(ns split.split)

(defn merge-input [input population]
  (merge input {:population population}))

(defn split-population [n population]
  "divide the population into groups of no more than n"
  (if (<= (count population) n)
    [population]
    (concat
      [(subvec population 0 n)]
      (split-population n (subvec population n)))))

(defn split-input [input]
  "generate inputs for islands derived from a starter population"
  (let [{children :children population :population} input]
    (map (partial merge-input input) (partition children population))))
