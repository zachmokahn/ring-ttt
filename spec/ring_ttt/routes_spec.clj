(ns ring-ttt.routes-spec
  (:require [speclj.core :refer :all]
            [ring-ttt.routes :refer :all]))

(describe "SPEC: ring-ttt.routes"
  (context "#get-routes"
    (it "'/' gets the 'home' route"
      (should= ring-ttt.routes.home/home
               (get get-routes "/")))))
