(ns ring-ttt.controllers.game-controller
  (:require [ring-ttt.controllers.controller :refer :all]
            [ttt.board :refer :all]))

(defn parse-game-mode [request]
  (get-in request [:params "gameMode"]))

(defn parse-game-difficulty [request]
  (get-in request [:params "gameDifficulty"]))

(defn parse-new-game-post [request]
  {:board       new-board
   :mode        (parse-game-mode request)
   :difficulty  (parse-game-difficulty request)})

(defn game? [request]
  (let [uri  (get-uri request)
        path (split-uri uri)]
  (= "game" (first (rest path)))))

(defn new-game [request]
  (parse-new-game-post request))

(defn game-post-controller [request]
  (let [uri  (get-uri request)
        path (split-uri uri)]
    (if (= "new-game" (last path)) new-game)))

(defn game-controller [request]
  (if (post? request) (game-post-controller request)))
