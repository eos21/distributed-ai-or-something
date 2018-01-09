(def operations '(+ - * /))
(def mutation-rate 0.05)
(def population-size 100)
(def gene '(fn [x]))

(defn rand-gene [genes]
  (if (empty? genes) nil (rand-nth genes))
)

(defn pick-gene [& args]
  (rand-gene (remove nil? args))
)

(defn mutate-gene [gene]
  (println "mutating" gene)
  (if (some #(= gene %) operations)
    (rand-nth operations)
    (if (number? gene)
      (if (> (rand) 0.5)
        (- gene 1)
        (+ gene 1)
      )
      gene
    )
  )
)

(defn possibly-mutate-gene [gene]
  (if (> (rand) mutation-rate)
  gene
  (mutate-gene gene))
)

(defn mutate [creature]
  (map possibly-mutate-gene creature)
)

(defn breed [population]
  (let [
      dad (pad 100 (rand-nth population) nil)
      mom (pad 100 (rand-nth population) nil)
    ]
    (mutate (remove nil? (map pick-gene dad mom)))
  )
)

(defn pad [n coll val]
  (take n (concat coll (repeat val))))

(defn fitness [creature]
  ((eval (concat gene [creature])) 1)
)

(defn epoch [population]
  (let [new-population (distinct (concat population (take population-size (repeatedly #(breed population)))))]
    (take (/ population-size 10) (reverse (sort-by fitness new-population)))
  )
)

(def population '(
  (+ x 10)
  (- x 2 3)
  (* x 1)
))

(def population (epoch population))
(println population)
