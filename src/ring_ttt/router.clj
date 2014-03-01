(ns ring-ttt.router
  (:use clojure.string)
  (:require [ring-ttt.controllers.home-controller :refer :all]))

(defn router [request]
 (let [path (:uri request)
       controller (split path #"/")]
  (cond
    (home? controller) (home-controller request))))
