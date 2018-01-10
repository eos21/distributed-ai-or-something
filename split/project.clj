(defproject split "1.0.0"
  :description "Split a population input into island inputs"
  :url "https://github.com/computes/distributed-ai-or-something/tree/master/split"
  :license {:name "MIT"
             :url "https://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.9.0"]
    [org.clojure/data.json "0.2.6"]
    [proto-repl "0.3.1"]
  ]
  :main ^:skip-aot split.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
