(ns genetics.core
  (:gen-class)
  (:require [genetics.datagen :as datagen]
            [genetics.population]))

(defn run []
  (def generation '(
    (+ x 10)
    (- x 2)
    (* x 1)
  ))
  (dotimes [n 100]
    (println "epoch" n)
    (def generation (epoch generation (datagen/create-data 5)))
  )
    (print (first generation))
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run))
