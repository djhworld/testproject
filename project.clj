(defproject testproject "1.0.0-SNAPSHOT"
            :description "FIXME: write this!"
            :dependencies [[org.clojure/clojure "1.3.0"]
                           [noir "1.2.1"]]
            :dev-dependencies [[lein-cljsbuild "0.0.13"]]
            :cljsbuild
            {
            :source-path "src-cljs"
            :compiler
            {
             :output-to "resources/public/js/cljs.js"
             :optimizations :simple
             :pretty-print true
             }
            }
            :main testproject.server)
