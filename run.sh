#!/bin/bash

CHILDREN=100
CHILDREN_THAT_SURVIVE=10
EPOCHS=100
DATA_SIZE=100

generate_data() {
  set -e -o pipefail
  local algorithm="$1"

  pushd "datagen" &> /dev/null
  lein run "$DATA_SIZE" "$algorithm" | ipfs dag put
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

  pushd "popgen" &> /dev/null
  lein run "$seed" "$CHILDREN" | ipfs dag put
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

  pushd "genetics-map" &> /dev/null

  for map_ref in "${map_refs[@]}"; do
    ipfs dag get "$map_ref" \
    | lein run \
    | ipfs dag put
  done

  popd &> /dev/null
}

run_reduce() {
  set -e -o pipefail

  local reduce_ref="$1"
  pushd "genetics-reduce" &>/dev/null

  ipfs dag get "$reduce_ref" \
  | lein run \
  | ipfs dag put

  popd &>/dev/null
}

run_split() {
  set -e -o pipefail

  local split_ref="$1"
  pushd "genetics-split" &>/dev/null

  ipfs dag get "$split_ref" \
  | lein run \
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
  # echo "data_ref: $data_ref"

  pop_ref="$(generate_population "$seed")"
  # echo "pop_ref: $pop_ref"

  split_ref="$(generate_split_input "$seed" "$data_ref" "$pop_ref")"
  # echo "split_ref: $split_ref"

  split_output_ref="$(run_split "$split_ref")"
  # echo "split_output_ref: $split_output_ref"

  map_refs="$(generate_map_inputs "$split_output_ref")"
  # echo "map_refs: $map_refs"

  map_output_refs="$(run_maps "$map_refs")"
  # echo "map_output_refs: $map_output_refs"

  reduce_ref="$(generate_reduce_input "$map_output_refs")"
  # echo "reduce_ref: $reduce_ref"

  run_reduce "$reduce_ref"
}
main "$@"
