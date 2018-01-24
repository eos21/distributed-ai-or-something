#!/bin/bash

# INPUT=zdpuAyZi5GqQF6qHBqwRRyJuzeWM1WYEt6afh3GWBNpsSu57M # 10 pop
INPUT=zdpuB2XqJBvkNWcYXyMriZ4WSF3ss1ALiDjdSwPqhC2QSQqJm # (* 2 x x)

main() {
  set -e -o pipefail
  local input="${1:-$INPUT}"

  ipfs dag get "$input" \
  | env LEIN_FAST_TRAMPOLINE=y lein trampoline run
}
main "$@"
