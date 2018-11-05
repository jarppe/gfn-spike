(defproject gcc-fn-spike "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.439"]]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :cljsbuild {:builds [{:id           "prod"
                        :source-paths ["src"]
                        :compiler     {:main          spike.core
                                       :output-to     "target/index.js"
                                       :target        :nodejs
                                       :source-map    false
                                       :optimizations :simple}}]}
  :aliases {"prod" ["do"
                    ["clean"]
                    ["cljsbuild" "once" "prod"]]})
