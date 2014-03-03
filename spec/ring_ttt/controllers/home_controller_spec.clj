(ns ring-ttt.controllers.home-controller-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.controllers.home-controller :refer :all]))

(describe "Controller -> Home Spec:"
  (describe "#home?"
    (it "true if meets criteria for home controller"
      (should= true (home? [])))
    (it "false if doesn't meet home controller criteria"
      (should= false (home? ["" "test" "uri"]))))

  (describe "#home-page"
    (it "loads 'index.html' when called"
      (should-invoke ring-ttt.controllers.controller/respond-with
        {:with ["index.html"]}
      (home-page "test"))))

  (describe "#home-controller"
    (describe "HTTP request -> GET"
      (it "gets the home page"
        (with-redefs [ring-ttt.controllers.controller/get? (fn [_] true)]
        (should= home-page
                 (home-controller {:test "data"})))))))
