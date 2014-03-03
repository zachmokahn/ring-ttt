(ns ring-ttt.controllers.home-controller
  (:require [ring-ttt.controllers.controller :refer :all]))

(defn home? [request]
  (let [uri  (get-uri request)
        path (split-uri uri)]
  (empty? path)))

(defn home-page [_]
  (respond-with "index.html"))

(defn home-controller [response]
  (if (get? response) home-page))
