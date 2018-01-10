(ns split.input
  (:require [clojure.data.json :as json]))

(defn parse-input [str] (json/read-str str :key-fn keyword))
