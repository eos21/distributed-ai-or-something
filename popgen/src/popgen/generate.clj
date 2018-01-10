(ns popgen.generate)

(defn random-number []
  (rand-nth (range 10)))

(defn random-op []
  (rand-nth ['+ '- '*]))

(defn creature [i]
  "Generate a single creature"
  (str (list (random-op) (random-number) (random-number))))

(defn population [n]
  "Generate a starter population"
  (map creature (range n)))
