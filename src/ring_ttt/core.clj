(ns ring-ttt.core
  (:require [ring-ttt.router :refer [router]])
  (:use [ring.middleware.params]
        [ring.middleware.file]
        [ring.util.response]
        [ring.middleware.json]))

(defn handler [request]
  (let [body (router request)]
    (response (body request))))

(def app
  (-> handler
  (wrap-json-response)
  (wrap-params)
  (wrap-file "public")))
