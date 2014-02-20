(ns ring-ttt.routes
  (:require [ring-ttt.routes.home :refer :all]
            [ring-ttt.routes.four-oh-four :refer :all]))

(def page-not-found four-oh-four)

(def get-routes { "/" home })

(def post-routes {})
