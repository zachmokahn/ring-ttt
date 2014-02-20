(ns ring-ttt.core
  (:require [ring-ttt.routes :refer :all]))

(defn handler [request]
  (let [body (controller request)]
  (body request)))
