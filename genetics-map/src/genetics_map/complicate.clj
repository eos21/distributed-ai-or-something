(ns genetics-map.complicate)

(defn replace-exponent [expr]
  (if (coll? expr)
    (if (not (= (first expr) '**))
      (map replace-exponent expr)
      (map replace-exponent (concat '(*) (take (last expr) (repeat (first (rest expr)))) ))
    )
    expr
  )
)

(defn complicate-gene [gene]
  (replace-exponent gene))
