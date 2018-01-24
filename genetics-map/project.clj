(defproject genetics-map "2.2.2"
  :description "Simulate multiple breed/culling epochs on an island"
  :url "https://github.com/computes/distributed-ai-or-something/tree/master/genetics-map"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.9.0"]
    [org.clojure/data.json "0.2.6"]
    [proto-repl "0.3.1"]
    [random-seed "1.0.0"]
    [expresso "0.2.2"]
  ]
  :main ^:skip-aot genetics-map.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
