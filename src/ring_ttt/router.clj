(ns ring-ttt.router
  (:use clojure.string)
  (:require [ring-ttt.controllers.home-controller :refer :all]
            [ring-ttt.controllers.invalid-controller :refer :all]))

(defn router [request]
  (let [uri        (:uri request)
        controller (split uri #"/")]
    (cond
      (home? controller) (home-controller request)
    :else (invalid-controller request))))
