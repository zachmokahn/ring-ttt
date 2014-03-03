(ns ring-ttt.controllers.game-controller
  (:require [ring-ttt.controllers.controller :refer :all]))

(defn game? [request]
  (let [uri  (get-uri request)
        path (split-uri uri)]
  (= "game" (first (rest path)))))

(defn new-game [request]
  ("TODO"))

(defn game-post-controller [request]
  (let [uri  (get-uri request)
        path (split-uri uri)]
    (if (= "new-game" (last path)) new-game)))

(defn game-controller [request]
  (if (post? request) (game-post-controller request)))
