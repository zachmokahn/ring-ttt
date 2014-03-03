(ns ring-ttt.router-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.router :refer :all]))

(describe "Router Spec:"
  (let [test-request { :uri "/test/uri" }]
    (describe "#router"
      (it "obtains and parses the URI properly"
        (should-invoke
          ring-ttt.controllers.home-controller/home?
            {:with [["" "test" "uri"]]}
          (router test-request))))))
