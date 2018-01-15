#!/bin/bash

# INPUT=zdpuAuH6qA5dqPC89LyTUgbkJLxyGuKDYW57DYNmPzCs6fb7E # x^3
# INPUT=zdpuAogbJ8X93BZuucoPEem1GGnmYUet5DsTGfcyrypWXahxo # x^3 - x
INPUT=zdpuAmtKj6ZTeXQGNfS1htFoUytkEXYcrJzB58J43ryroeNU3 # 2x^2+7

main() {
  set -e -o pipefail

  ipfs dag get "$INPUT" \
  | env LEIN_FAST_TRAMPOLINE=y lein trampoline run
}
main "$@"
