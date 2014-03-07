(ns ring-ttt.controllers.home-controller
  (:require [ring-ttt.constants :refer [INDEX]]
            [ring-ttt.controllers.controller :refer :all]))

(defn home? [request]
  (let [path (get-path request)]
    (empty? path)))

(defn home-page [_]
  (respond-with INDEX))

(defn home-controller [response]
  (if (get? response) home-page))
