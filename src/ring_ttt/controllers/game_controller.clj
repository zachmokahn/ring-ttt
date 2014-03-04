(ns ring-ttt.controllers.game-controller
  (:require [ring-ttt.controllers.controller :refer :all]
            [ring-ttt.constants :refer :all]
            [ring-ttt.interface :refer :all]
            [ttt.board :refer :all]))

(defn parse-params [parameter request]
  (get-in request [:params parameter]))

(defn parse-game-mode [request]
  (parse-params MODE request))

(defn parse-game-difficulty [request]
  (parse-params DIFFICULTY request))

(defn parse-game-board [request]
  (parse-params BOARD request))

(defn parse-game-turn [request]
  (parse-params TURN request))

(defn parse-new-game-params [request]
  {:board       new-board
   :turn        (parse-game-turn request)
   :mode        (parse-game-mode request)
   :difficulty  (parse-game-difficulty request)})

(defn parse-make-move-params [request]
  {:board       (parse-game-board request)
   :turn        (parse-game-turn request)
   :mode        (parse-game-mode request)
   :difficulty  (parse-game-difficulty request)})

(defn new-game [request]
  (play-game (parse-new-game-params request)))

(defn make-move [request])

(defn game-post-controller [request]
  (let [uri  (get-uri request)
        path (split-uri uri)]
    (cond (= NEW-GAME  (last path)) new-game
          (= MAKE-MOVE (last path)) make-move)))

(defn game-controller [request]
  (if (post? request) (game-post-controller request)))

(defn game? [request]
  (let [uri  (get-uri request)
        path (split-uri uri)]
  (= GAME (first (rest path)))))
