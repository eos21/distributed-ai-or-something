(defproject genetics-reduce "1.0.0"
  :description "Bring the island survivers back for a reunion culling"
  :url "https://github.com/computes/distributed-ai-or-something/tree/master/genetics-reduce"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.9.0"]
    [org.clojure/data.json "0.2.6"]
  ]
  :main ^:skip-aot genetics-reduce.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
