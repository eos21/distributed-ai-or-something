(ns genetics-map.core
  (:gen-class)
  (:require
    [clojure.data.json :as json]
    [clojure.string :refer [join]]
    [genetics-map.input :refer [parse-input]]
    [genetics-map.simulate :as simulate]
    [random-seed.core :refer [set-random-seed!]]
    [numeric.expresso.core :refer [simplify]]))

(defn println-error [& strs]
  (.println *err* (join " " strs)))

; (defn print-fitness [creature]
;   (println
;     (simulate/fitness data creature)
;     creature))

(defn try-simplify [expression]
  (try
    (simplify expression)
    (catch ArithmeticException e expression)))

(defn stringify-creature [creature]
  (str (apply list creature)))

(defn format-creature [creature]
  (stringify-creature (try-simplify creature)))

(defn run [population trainingData children childrenThatSurvive epochs seed]
  "Simulate an island"
  (set-random-seed! seed)
  (def generation population)
  (dotimes [n epochs]
    (println-error "epoch" (format "%s/%s" n epochs))
    (def generation (simulate/epoch generation trainingData children childrenThatSurvive))
    (println-error
      (simulate/fitness trainingData (first generation))
      (format-creature (first generation)))
    (println-error "original" (stringify-creature (first generation))))

  (println (json/write-str {
    :population (map stringify-creature generation)
    :trainingData trainingData
    :children children
    :childrenThatSurvive childrenThatSurvive
    :epochs epochs
    :seed seed
    })))

(defn -main
  "Simulate an island with input from stdin"
  [& args]
  (let [{:keys [population trainingData children childrenThatSurvive epochs seed]} (parse-input (slurp *in*))]
    (run population trainingData children childrenThatSurvive epochs seed)))
