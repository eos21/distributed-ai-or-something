(ns genetics.core
  (:gen-class)
  (:require [genetics.datagen :as datagen]
            [genetics.population]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (dotimes [n 10]
    (println "epoch" n)
    (def population (epoch population []))
  ))


  
