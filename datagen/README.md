# Datagen

Generate a bunch of training data for the genetic algorithm.

## Usage

The usage is `lein run <n> <algorithm>`.

* `<n>` indicates the number of (x y) pairs to generate
* `<algorithm>` is a clojure function that takes x and outputs y. For example:
  * '(* x 2)' # y = 2x
  * '(* x (+ 2 x))' # y = 2x + x²

The output is a JSON string of (x y) pairs.

```shell
#!/bin/bash

lein run 5 '(* x 2)'
#> [[0,0],[1,2],[2,4],[3,6],[4,8]]
```
