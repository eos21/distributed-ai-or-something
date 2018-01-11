(defproject popgen "1.0.0"
  :description "Generate a starter population"
  :url "https://github.com/computes/distributed-ai-or-something/tree/master/popgen"
  :license {:name "MIT"
             :url "https://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.9.0"]
    [org.clojure/data.json "0.2.6"]
    [random-seed "1.0.0"]
  ]
  :main ^:skip-aot popgen.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
