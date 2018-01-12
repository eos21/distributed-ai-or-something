(ns genetics-split.core
  (:gen-class)
  (:require
    [clojure.data.json :as json]
    [genetics-split.input :refer [parse-input]]
    [genetics-split.split :refer [split-input]]
    [random-seed.core :refer [set-random-seed!]]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [in    (slurp *in*)
        input (parse-input in)
        {:keys [childrenThatSurvive population seed]} input]

    (set-random-seed! seed)
    (println (json/write-str (split-input input childrenThatSurvive population)))))
