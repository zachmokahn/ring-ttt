(ns ring-ttt.controllers.invalid-controller
  (:require [ring-ttt.constants :refer [INVALID]]
            [ring-ttt.controllers.controller :refer :all]))

(defn page-not-found [_]
  (respond-with INVALID))

(defn invalid-controller [_] page-not-found)
