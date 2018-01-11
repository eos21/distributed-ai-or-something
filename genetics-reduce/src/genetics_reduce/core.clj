(ns genetics-reduce.core
  (:gen-class)
  (:require
    [clojure.data.json :as json]
    [clojure.string :refer [join]]
    [genetics-reduce.input :refer [parse-input]]
    [genetics-reduce.sort :refer [fitness sort-population]]))

(defn fatal [& strs]
    "Print a message and exit 1"
    (println "USAGE: lein run <n> <algorithm>\n")
    (println "Example: lein run 100 '(* x 2)'\n")
    (apply println strs)
    (System/exit 1))

(defn println-error [& strs]
  (.println *err* (join " " strs)))

(defn sanity-check-input [input]
  (let [data (map :data input)]
    (if-not (apply = data) (fatal "data did not match" data))))

(defn format-creature [creature]
  (str (apply list creature)))

(defn -main
  "Bring the island survivers back for a reunion culling"
  [& args]
  (let [input (parse-input (slurp *in*))]
    (sanity-check-input input)
    (let [data       (:trainingData (first input))
          population (apply concat (map :population input))
          winner     (first (sort-population data population))]
      (println (json/write-str {
        :winner  (format-creature winner)
        :fitness (fitness data winner)
      })))))
