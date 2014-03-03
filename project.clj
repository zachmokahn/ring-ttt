(defproject ring-ttt "0.1.0-SNAPSHOT"
  :description "ring implementation of tic tac toe"
  :url "http://www.github.com/zachmokahn/ring-ttt"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ttt/ttt "1.0.0"]
                 [ring/ring-core "1.2.1"]
                 [ring/ring-jetty-adapter "1.2.1"]]
  :profiles {:dev {:dependencies [[speclj "3.0.1"]]}}
  :plugins [[speclj "3.0.1"]
            [lein-ring "0.8.10"]]
  :ring { :handler ring-ttt.core/handler }
  :test-paths ["spec"])
