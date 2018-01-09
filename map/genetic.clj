(def operations '(+ - * /))
(def mutation-rate 0.5)
(def population-size 100)
(def gene '(fn [x]))

(def population '(
  (+ x 10)
  (- x 2 3)
  (* x 1)
))

(defn rand-gene [genes]
  (if (empty? genes) nil (rand-nth genes))
)

(defn pick-gene [& args]
  (rand-gene (remove nil? args))
)

(defn breed [population]
  (let [
      dad (pad 100 (rand-nth population) nil)
      mom (pad 100 (rand-nth population) nil)
    ]
    (remove nil? (map pick-gene dad mom))
  )
)

(defn pad [n coll val]
  (take n (concat coll (repeat val))))

(defn fitness [creature]
  ((eval (concat gene [creature])) 1)
)

(def population (distinct (concat population
  (take population-size (repeatedly #(breed population))))))

(println (map fitness population))
(reverse (sort-by fitness population))
