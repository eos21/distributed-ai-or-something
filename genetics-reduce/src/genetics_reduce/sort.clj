(ns genetics-reduce.sort)

(defn abs [n]
  (if (< 0 n)
    n
    (* -1 n)))

(defn mean [col]
  (/ (reduce + col) (count col)))

(defn fitness-on-data [creature creatureFn [x y]]
  (+ (abs (- y (creatureFn x))) (* (count (flatten creature)) 0.01)))

(defn fitness [data creature]
  (let [creatureFn (eval (concat '(fn [x]) [creature]))]
    (mean (map (partial fitness-on-data creature creatureFn) data))))

(defn sort-population [data population]
  "Sort a population using the fitness function"
  (sort-by (partial fitness data) population))
