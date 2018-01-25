#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
SEED=0

cleanup(){
  cd "$DIR"
}
trap cleanup EXIT

generate_input() {
  local formula="$1"

  "$DIR/generate-input.sh" "$SEED" "$formula"
}

main(){ 
  set -e -o pipefail
  local formula input_hash

  formula="$1"

  echo "generating input (takes ~10s)"
  pushd "$DIR/genetics-map" > /dev/null
  input_hash="$(generate_input "$formula")"
  ipfs dag get "$input_hash"
  echo ""
  echo "running genetic algorith to solve for '$formula'"
  ipfs dag get "$input_hash" | lein run
  popd > /dev/null
}
main "$@"
