(ns genetics-map.complicate-test
  (:require [clojure.test :refer :all]
            [genetics-map.complicate :refer :all]))

(deftest test-complicate
  (testing "When given a expression it should return that expression"
    (is (= '(+ 1 2) (complicate-gene '(+ 1 2)))))

  (testing "When given a expression with a ** it should expand into multiplication"
    (is (= '(* x x x) (complicate-gene '(** x 3)))))

  (testing "When given a expression with a ** it should expand into multiplication"
    (is (= '(+ (* x x x) 1) (complicate-gene '(+ (** x 3) 1))))))
