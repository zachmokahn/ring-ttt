(ns ring-ttt.controllers.invalid-controller-spec
  (:require [ring-ttt.spec-helper :refer :all]
            [speclj.core :refer :all]
            [ring-ttt.controllers.invalid-controller :refer :all]))

(describe "Controller -> Invalid Spec:"
  (describe "#page-not-found"
    (it "loads '404.html' when called"
      (should-invoke ring-ttt.controllers.controller/respond-with
        {:with ["404.html"]}
        (page-not-found _test-request))))

  (describe "#invalid-controller"
    (it "invokes page-not-found"
      (should= page-not-found
               (invalid-controller _test-request)))))
