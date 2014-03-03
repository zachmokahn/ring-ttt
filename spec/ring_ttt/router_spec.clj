(ns ring-ttt.router-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.router :refer :all]))

(describe "Router Spec:"
  (let [test-request { :uri "/test/uri" }]
    (describe "#router"
      (it "-> propper controller if route exists"
      (with-redefs
        [ring-ttt.controllers.home-controller/home? (fn [_] true)]
      (should-invoke
        ring-ttt.controllers.home-controller/home-controller
          {:with [test-request]}
        (router test-request))))
      (it "-> invalid-controller if route doesn't exist"
        (should-invoke
          ring-ttt.controllers.invalid-controller/invalid-controller
            {:with [test-request]}
             (router test-request))))))
