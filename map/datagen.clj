(ns datagen)

(defn create-data-point [i]
  "generate a (x y) pair where y = 2x"
  (list i (* 2 i)))

(defn create-data [n]
  "generate n (x y) pairs where y = 2x"
  (map create-data-point (range n)))

; (create-data 5)
; (create-data 10)
; (create-data 1)
