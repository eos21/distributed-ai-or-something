(ns split.split)

(defn merge-input [input population]
  (merge input {:population population}))

(defn split-input [input]
  "generate inputs for islands derived from a starter population"
  (let [{children :children population :population} input]
    (map (partial merge-input input) (partition children children nil population))))
