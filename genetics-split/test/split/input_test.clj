(ns split.input-test
  (:require [clojure.test :refer :all]
            [split.input :refer :all]))

(deftest when-standard-input
  (testing "When given a standard input"
    (is (=
      (parse-input "{
        \"population\": [\"(+ 1 x)\"],
        \"trainingData\": [[0,0],[1,2]],
        \"children\":100,
        \"childrenThatSurvive\":10,
        \"epochs\": 100,
        \"seed\": 0
      }")
      {
        :population          ["(+ 1 x)"]
        :trainingData        [[0,0], [1,2]]
        :children            100
        :childrenThatSurvive 10
        :epochs              100
        :seed                0}))))
