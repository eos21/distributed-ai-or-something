(defproject datagen "1.0.0"
  :description "Generate a data set for running the learning algorithm."
  :url "https://github.com/computes/distributed-ai-or-something/tree/master/datagen"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.9.0"]
    [org.clojure/data.json "0.2.6"]
  ]
  :main ^:skip-aot datagen.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
