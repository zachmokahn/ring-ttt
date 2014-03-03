(ns ring-ttt.router-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.router :refer :all]))

(describe "Router Spec:"
  (let [test-request { :uri "/test/uri" }]
    (describe "#router"
      (it "invalid-controller if route doesn't exist"
        (should-invoke
          ring-ttt.controllers.invalid-controller/invalid-controller
            {:with [test-request]}
             (router test-request))))))
