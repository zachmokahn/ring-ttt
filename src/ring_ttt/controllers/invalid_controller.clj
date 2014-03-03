(ns ring-ttt.controllers.invalid-controller
  (:require [ring-ttt.controllers.controller :refer :all]))

(defn page-not-found [_]
  (respond-with "404.html"))

(defn invalid-controller [_] page-not-found)
