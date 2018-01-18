(ns genetics-map.simulate-test
  (:require [clojure.test :refer :all]
            [genetics-map.simulate :refer :all]))

(deftest test-nest-gene
  (testing "When given a list it should return a list"
    (is (coll? (nest-gene '(+ 1 2)))))

  (testing "When given a list it should return a list containing that list"
    (is (some #{'(+ 1 2)} (nest-gene '(+ 1 2)))))

  (testing "When given a list it should return a list that starts with an operation"
    (set-random-seed! 0)
    (let [operand (first (nest-gene '(+ 1 2)))]
      (is (= '* operand)))))

(deftest test-append
  (testing "When given a list it should return a list"
    (is (coll? (append-gene '(+ 1 2)))))

  (testing "When given a list there should exist a random seed for which it returns x"
    (set-random-seed! 0)
    (is (= 'x (last (append-gene '(+ 1 2))))))

  (testing "When given a list it should sometimes append an x"
    (set-random-seed! 0)
    (is (= 'x (last (append-gene '(+ 1 2)))))))

(deftest test-remove
  (testing "When given a list with more than 2 items, it should remove an item from the list"
    (is (= '(+ 1) (remove-gene '(+ 1 2))))))
