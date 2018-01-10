(ns split.split-test
  (:require [clojure.test :refer :all]
            [split.split :refer :all]))

(deftest test-split-input
  (testing "when given a standard input"
    (is (= [
      {:population [1] :childrenThatSurvive 1}
      {:population [2] :childrenThatSurvive 1}
    ]
    (split-input {:population [1 2] :childrenThatSurvive 1})))))
