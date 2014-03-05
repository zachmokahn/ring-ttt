(ns ring-ttt.core
  (:require [ring-ttt.router :refer [router]])
  (:use [ring.middleware.params]))

(defn handler [request]
  (let [body (router request)] (body request)))

(def app (wrap-params handler))
