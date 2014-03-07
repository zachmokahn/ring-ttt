(ns ring-ttt.controllers.game-controller
  (:require [ring-ttt.controllers.controller :refer :all]
            [ring-ttt.constants :refer :all]
            [ring-ttt.parameter-parser :refer :all]
            [ring-ttt.interface :refer :all]))

(defn new-game-params [request]
  {:turn        (parse-game-turn request)
   :mode        (parse-game-mode request)
   :difficulty  (parse-game-difficulty request)})

(defn make-move-params [request]
  {:board       (parse-game-board request)
   :turn        (parse-game-turn request)
   :mode        (parse-game-mode request)
   :move        (parse-game-move request)
   :difficulty  (parse-game-difficulty request)})

(defn new-game [request]
  (start-game (new-game-params request)))

(defn make-move [request]
  (play-game (make-move-params request)))

(defn game-controller [request]
  (let [path (get-path request)]
    (cond (= NEW-GAME  (last path)) new-game
          (= MAKE-MOVE (last path)) make-move)))

(defn game? [request]
  (let [path (get-path request)]
  (= GAME (first (rest path)))))
