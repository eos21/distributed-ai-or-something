(ns genetics.math)

(defn mean [col]
  (/ (reduce + col) (count col)))
  
