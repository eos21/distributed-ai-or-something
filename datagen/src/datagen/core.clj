(ns datagen.core
  (:gen-class)
  (:require [clojure.data.json :as json]))

(defn fatal [msg]
  "Print a message and exit 1"
  (println "USAGE: lein run <n> <algorithm>\n")
  (println "calculates (x y) pairs for all x values where -n <= x <= n")
  (println "Example: lein run 100 '(* x 2)'\n")
  (println msg)
  (System/exit 1))

(defn generate-data-point [f x]
  "generate a (x y) pair where y = f(x)"
  (list x (f x)))

(defn make-negative [n]
  (* n -1))

(defn generate-data [f n]
  "generate n (x y) pairs where y = f(x)"
  (let [indexes         (range n)
        negativeIndexes (map make-negative (rest indexes))
        positiveData (map (partial generate-data-point f) indexes)
        negativeData (map (partial generate-data-point f) negativeIndexes)]
        (concat (reverse negativeData) positiveData)))

(generate-data #(+ % 1) 4)

(defn parse-func [funcStr]
  "Parses a function from a string and returns a function that takes 'x'"
  (eval (concat '(fn [x]) (list (read-string funcStr)))))

(defn -main
  "Generate a bunch of sample data given a clojure function with x"
  [& args]
  (let [[nStr funcStr] args]
    (if (nil? nStr) (fatal "Missing required parameter <n>"))
    (if (nil? funcStr)  (fatal "Missing required parameter <algorithm>"))
    (let [
      n (Integer/parseInt nStr)
      f (parse-func funcStr)]

      (println (json/write-str (generate-data f n))))))
