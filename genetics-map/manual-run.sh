# EQUATION=zdpuAuH6qA5dqPC89LyTUgbkJLxyGuKDYW57DYNmPzCs6fb7E # x^3
EQUATION=zdpuAmtKj6ZTeXQGNfS1htFoUytkEXYcrJzB58J43ryroeNU3 # 2x^2+7
ipfs dag get $EQUATION | env LEIN_FAST_TRAMPOLINE=y lein trampoline run
