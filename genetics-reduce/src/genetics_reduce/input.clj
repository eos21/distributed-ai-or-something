(ns genetics-reduce.input
  (:require [clojure.data.json :as json]))

(defn parse-population [population]
  (map read-string population))

(defn parse-item [item]
  (merge item {:population (parse-population (:population item))}))

(defn parse-input [str]
  (let [input (json/read-str str :key-fn keyword)]
    (apply list (map parse-item input))))
