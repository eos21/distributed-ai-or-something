(def operations '(+ - * /))
(def mutation-rate 0.05)
(def population-size 100)

(defn pad [n coll val]
  (take n (concat coll (repeat val))))

(defn rand-gene [genes]
  (if (empty? genes) nil (rand-nth genes))
)

(defn pick-gene [& args]
  (rand-gene (remove nil? args))
)

(declare mutate)

(defn random-operation-gene [gene] (rand-nth operations))

(defn random-number-gene [gene]
  (rand-nth [
    (list '- gene 1)
    (list '+ gene 1)
    (list '+ gene 'x)
  ])
)

(defn mutate-gene [gene]
  (cond
    (some #(= gene %) operations) (random-operation-gene gene)
    (list? gene) (mutate gene)
    :else (random-number-gene gene)
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

(defn fitness [data creature]
  (let [
    creatureFn (eval (concat '(fn [x]) [creature]))
    score (creatureFn 1)
  ]
    score
  )
)

(defn epoch [population data]
  (let [new-population (distinct (concat population (take population-size (repeatedly #(breed population)))))]
    (println "population size" (count new-population))
    (take 10 (reverse (sort-by (partial fitness data) new-population)))
  )
)

(def population '(
  (+ x 10)
  (- x 2)
  (* x 1)
))

(dotimes [n 10]
  (println "epoch" n)
  (def population (epoch population []))
)

(println
  (fitness [] (first population))
  (count population) population
)
