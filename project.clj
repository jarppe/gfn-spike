(defproject gcc-fn-spike "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.439"]]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :source-paths ["src"]
  :cljsbuild {:builds [{:id           "prod"
                        :source-paths ^:replace ["src"]
                        :compiler     {:main           spike.core
                                       :output-to      "target/dist/index.js"
                                       :target         :nodejs
                                       :source-map     false
                                       :optimizations  :advanced
                                       :externs        ["externs/core.js"
                                                        "externs/http.js"
                                                        "externs/misc.js"]
                                       :parallel-build true}}]}
  :aliases {"prod" ["do"
                    ["clean"]
                    ["cljsbuild" "once" "prod"]]})
