(ns datagen.generate)

(defn generate-data-point [x]
  "generate a (x y) pair where y = 2x"
  ; y = 2(x + 3)
  ; y = 2x + 6
  ; (list x (* 2 x)))
  (list x (* 2 (+ x 3))))

(defn generate-data [n f]
  "generate n (x y) pairs where y = 2x"
  (map f (range n)))

; (create-data 5)
; (create-data 10)
; (create-data 1)
