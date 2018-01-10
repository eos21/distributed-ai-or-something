(ns split.core
  (:gen-class)
  (:require [split.input :refer [parse-input]]
            [split.split :refer [split-input]]
            [clojure.data.json :as json]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [in    (slurp *in*)
        input (parse-input in)]
    (println (json/write-str (split-input input)))))
