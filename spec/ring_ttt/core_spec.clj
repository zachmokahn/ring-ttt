(ns ring-ttt.core-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.core :refer :all]))

(describe "SPEC: ring-ttt.core"
  (let [request {:request-method :delegates-to-router
                 :uri "/null-pointer-because-fake"}]
  (context "#handler"
    (it "controller=>ring-ttt.router/controller"
      (should-throw java.lang.NullPointerException
                    (handler request))))))
