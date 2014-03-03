(ns ring-ttt.router
  (:require [ring-ttt.controllers.controller :refer :all]
            [ring-ttt.controllers.home-controller :refer :all]
            [ring-ttt.controllers.game-controller :refer :all]
            [ring-ttt.controllers.invalid-controller :refer :all]))

(defn router [request]
    (cond
      (home? request) (home-controller request)
      (game? request) (game-controller request)
    :else (invalid-controller request)))

(+ (- 2 1) 1)
