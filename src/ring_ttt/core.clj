(ns ring-ttt.core
  (require [ring-ttt.router :refer [router]]))

(defn handler [request]
  (let [body (router request)]
    (body request)))
