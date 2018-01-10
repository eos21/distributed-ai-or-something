(ns split.split-test
  (:require [clojure.test :refer :all]
            [split.split :refer :all]))

(deftest test-split-population
  (testing "when given a population of 0 should return an empty island"
    (is (= [[]] (split-population 3 []))))

  (testing "when given a population count matching the children should return a full island"
    (is (= [[1 2 3]] (split-population 3 [1 2 3]))))

  (testing "when given a population count less than the children should return enough islands"
    (is (= [[1 2], [3]] (split-population 2 [1 2 3]))))

  (testing "when given a larger set should return in order"
    (is (= [[1 2] [3 4] [5 6] [7]] (split-population 2 [1 2 3 4 5 6 7]))))
)

(deftest test-split-input
  (testing "when given a standard input"
    (is (= [
      {:population [1] :children 1}
      {:population [2] :children 1}
    ]
    (split-input {:population [1 2] :children 1})))))
