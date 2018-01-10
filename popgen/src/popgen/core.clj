(ns popgen.core
  (:gen-class)
  (:require [clojure.data.json :as json]
            [popgen.generate :as generate]))

(defn fatal [msg]
  "Print a message and exit 1"
  (println "USAGE: lein run <n>\n")
  (println "Example: lein run 100'\n")
  (println msg)
  (System/exit 1))

(defn -main
  "Generate a starter population"
  [& args]
  (let [[nStr] args]
    (if (nil? nStr) (fatal "Missing required parameter <n>"))
    (let [n (Integer/parseInt nStr)]
      (println (json/write-str (generate/population n))))))
