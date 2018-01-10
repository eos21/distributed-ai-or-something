(ns genetics.datagen)

(defn create-data-point [x]
  "generate a (x y) pair where y = 2x"
  ; y = 2(x + 3)
  ; y = 2x + 6
  (list x (* 2 x)))
  ; (list i (* 2 (+ i 3))))

(defn create-data [n]
  "generate n (x y) pairs where y = 2x"
  (map create-data-point (range n)))

; (create-data 5)
; (create-data 10)
; (create-data 1)
