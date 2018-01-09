(ns genetics.math)

(defn mean [col]
  (/ (reduce + col) (count col)))

(defn abs [n]
  (if (< 0 n)
    (* -1 n)
    n))
