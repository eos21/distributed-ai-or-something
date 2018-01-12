(ns genetics-split.split
  (:require [random-seed.core :refer [rand rand-int rand-nth]])
  (:refer-clojure :exclude [rand rand-int rand-nth]))

(defn merge-input [input population]
  (merge input {:population population}))

(defn without-nth [population n]
  (keep-indexed #(if-not (= n %1) %2) population))

; can't use clojure shuffle cause it doesn't take a seed
(defn shuffle-population [population]
  (if (<= (count population) 1) population
    (let [n (rand-int (count population))]
      (conj (shuffle-population (without-nth population n)) (nth population n)))))

(defn split-input [input n population]
  "generate inputs for islands derived from a starter population"
  (map (partial merge-input input) (partition n n nil (shuffle-population population))))
