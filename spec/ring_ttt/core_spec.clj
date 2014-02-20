(ns ring-ttt.core-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.core :refer :all]))

(describe "SPEC: ring-ttt.core"
  (let [request {:request-method :test, :uri "/test"}]
    (it "#handler=>ring-ttt.router/controller"
      (should= 404 (:status (handler request))))))
