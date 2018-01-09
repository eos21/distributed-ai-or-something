(def operations '(+ - * /))
(def mutation-rate 0.5)
(def population-size 10)
(def gene '(fn [x]))

(def population '(
  (+ x 1)
  (- x 2)
  (* x 10)
))

(println ((eval (concat gene (first population))) 1))


(defn pick-gene [dad-gene mom-gene]  
  (if (> (rand-int 2) 0) dad-gene mom-gene)
)

(defn breed [population]
  (let [
      dad (rand-nth population)
      mom (rand-nth population)
    ]
    (map pick-gene dad mom)
  )
)

(take population-size (repeatedly #(breed population)))
