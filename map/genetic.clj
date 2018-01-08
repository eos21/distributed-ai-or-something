(def operations '(+ - * /))
(def mutation-rate 0.5)
(def population-size 10)
(def gene '(fn [x]))

(def population '(
  (+ x 1)
  (- x 2)
))

(println ((eval (concat gene (first population))) 1))

(defn breed [population]
  (let [
      creature1 (rand-nth population)
      creature2 (rand-nth population)
    ]    
  )
)

(take 5 (repeatedly #(breed population)))
