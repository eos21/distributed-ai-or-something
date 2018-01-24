#!/bin/bash

CHILDREN=20
CHILDREN_THAT_SURVIVE=5
EPOCHS=50
DATA_SIZE=10

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

generate_data() {
  set -e -o pipefail
  local algorithm="$1"

  pushd "$DIR/datagen" &> /dev/null
  env LEIN_FAST_TRAMPOLINE=y lein trampoline run "$DATA_SIZE" "$algorithm" | ipfs dag put
  popd &> /dev/null
}

generate_map_inputs() {
  set -e -o pipefail
  local split_output_ref="$1"
  local count
  count="$(ipfs dag get "$split_output_ref" | jq '. | length - 1')"

  for i in $(seq 0 "$count"); do
    ipfs dag get "$split_output_ref/$i" | ipfs dag put
  done
}

generate_population() {
  set -e -o pipefail
  local seed="$1"

  pushd "$DIR/popgen" &> /dev/null
  env LEIN_FAST_TRAMPOLINE=y lein trampoline run "$seed" "$CHILDREN" | ipfs dag put
  popd &> /dev/null
}

append() {
  set -e -o pipefail

  local list="$1"
  local item="$2"

  echo "$list" \
  | jq --argjson item "$item" '. += [$item]'
}

generate_reduce_input() {
  set -e -o pipefail

  local map_output_refs=( $1 )
  local output="[]"

  for ref in "${map_output_refs[@]}"; do
    output="$(append "$output" "$(ipfs dag get "$ref")")"
  done

  echo "$output" | ipfs dag put
}

generate_split_input() {
  set -e -o pipefail

  local seed="$1"
  local data_ref="$2"
  local pop_ref="$3"

  jq \
    --null-input \
    --argjson data "$(ipfs dag get $data_ref)" \
    --argjson population "$(ipfs dag get $pop_ref)" \
    --argjson seed "$seed" \
    --argjson children "$CHILDREN" \
    --argjson childrenThatSurvive "$CHILDREN_THAT_SURVIVE" \
    --argjson epochs "$EPOCHS" \
    '
    {
      "population": $population,
      "trainingData": $data,
      "children": $children,
      "childrenThatSurvive": $childrenThatSurvive,
      "epochs": $epochs,
      "seed": $seed
    }' \
  | ipfs dag put
}

run_maps() {
  set -e -o pipefail

  local map_refs=( $1 )

  pushd "$DIR/genetics-map" &> /dev/null

  for map_ref in "${map_refs[@]}"; do
    ipfs dag get "$map_ref" \
    | env LEIN_FAST_TRAMPOLINE=y lein trampoline run \
    | ipfs dag put
  done

  popd &> /dev/null
}

run_reduce() {
  set -e -o pipefail

  local reduce_ref="$1"
  pushd "$DIR/genetics-reduce" &>/dev/null

  ipfs dag get "$reduce_ref" \
  | env LEIN_FAST_TRAMPOLINE=y lein trampoline run \
  | ipfs dag put

  popd &>/dev/null
}

run_split() {
  set -e -o pipefail

  local split_ref="$1"
  pushd "$DIR/genetics-split" &>/dev/null

  ipfs dag get "$split_ref" \
  | env LEIN_FAST_TRAMPOLINE=y lein trampoline run \
  | ipfs dag put

  popd &>/dev/null
}

usage() {
  echo "USAGE: ./run.sh <seed> <algorithm>"
  echo ""
  echo "EXAMPLE: ./run.sh 100 '(+ 1 x)'"
}

main() {
  set -e -o pipefail
  local seed="$1"
  local algorithm="$2"

  if [ -z "$seed" ] || [ -z "algorithm" ]; then
    usage
    exit 1
  fi

  local data_ref pop_ref split_ref split_output_ref map_refs map_output_refs reduce_ref

  data_ref="$(generate_data "$algorithm")"
  pop_ref="$(generate_population "$seed")"
  split_ref="$(generate_split_input "$seed" "$data_ref" "$pop_ref")"
  echo "$split_ref"
}
main "$@"
