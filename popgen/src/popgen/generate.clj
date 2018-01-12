(ns popgen.generate
  (:require [random-seed.core :refer [rand rand-int rand-nth]])
  (:refer-clojure :exclude [rand rand-int rand-nth]))

(defn random-number []
  (rand-nth (conj (range 10) 'x)))

(defn random-op []
  (rand-nth ['+ '- '*]))

(defn creature [i]
  "Generate a single creature"
  (str (list (random-op) (random-number) (random-number))))

(defn population [n]
  "Generate a starter population"
  (map creature (range n)))
