(ns genetics-map.input
  (:require [clojure.data.json :as json]))

(defn parse-population [population]
  (map read-string population))

(defn parse-input [str]
  (let [input (json/read-str str :key-fn keyword)]
    (merge input {:population (parse-population (:population input))})))
