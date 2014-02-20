(ns ring-ttt.core
  (:require [ring-ttt.router :refer :all]))

(defn handler [request]
  (let [body (controller request)]
  (body request)))
