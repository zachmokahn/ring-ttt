(ns ring-ttt.core
  (:require [ring-ttt.router :refer [router]])
  (:use [ring.middleware.params]
        [ring.middleware.file]))

(defn handler [request]
  (let [body (router request)] (body request)))

(def app
  (-> handler
  (wrap-file "public")
  (wrap-params)))
