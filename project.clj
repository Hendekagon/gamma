(defproject org.clojars.hendekagon/gamma "auto"
  :description "glsl shaders made simple"
  :url "https://github.com/kovasb/gamma"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories [["clojars" {:sign-releases false}]]
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/clojurescript "1.9.521" :exclusions [clojure.core.rrb-vector]]
                 [quantum/org.clojure.core.rrb-vector "0.0.12"]
                 [fipp "0.6.9"]]
  :plugins [[org.clojars.cvillecsteele/lein-git-version "1.2.5"]
            [lein-cljsbuild "1.1.5" :exclusions [org.clojure/clojure]]]
  :source-paths ["src" "test"]
  :cljsbuild {:builds
              [
               {
                :id           "dev"
                :source-paths ["src"]
                :compiler     {
                               :asset-path           "js"
                               :source-map           true
                               :source-map-timestamp true
                               :pretty-print         true
                               :output-to            "public/js/main.js"
                               :output-dir           "public/js"
                               :optimizations        :none
                               :parallel-build       true
                               }
                }
               ]})