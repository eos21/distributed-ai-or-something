(ns popgen.core
  (:gen-class)
  (:require [clojure.data.json :as json]
            [popgen.generate :as generate]
            [random-seed.core :refer [set-random-seed!]]))

(defn fatal [msg]
  "Print a message and exit 1"
  (println "USAGE: lein run <seed> <n>\n")
  (println "Example: lein run 0 100'\n")
  (println msg)
  (System/exit 1))

(defn -main
  "Generate a starter population"
  [& args]
  (let [[seedStr nStr] args]
    (if (nil? seedStr) (fatal "Missing required parameter <seed>"))
    (if (nil? nStr) (fatal "Missing required parameter <n>"))
    (let [seed (Integer/parseInt seedStr)
          n    (Integer/parseInt nStr)]
      (set-random-seed! seed)
      (println (json/write-str (generate/population n))))))
