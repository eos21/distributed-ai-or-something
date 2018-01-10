(ns genetics.core
  (:gen-class)
  (:require [genetics.datagen :as datagen]
            [genetics.population :as population]))

(def data (datagen/create-data 100))

(defn print-fitness [creature]
  (println
    (population/fitness data creature)
    creature))

(defn run []
  (def generation '(
    (+ x 10)
    (- x 2)
    (* x 1)
  ))
  (dotimes [n 100]
    (println "epoch" n)
    (def generation (population/epoch generation data)))
    (doall (map print-fitness generation))
    (print-fitness (first generation)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run))
