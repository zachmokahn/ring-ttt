(ns ring-ttt.controllers.game-controller
  (:require [ring-ttt.controllers.controller :refer :all]
            [ring-ttt.constants :refer :all]
            [ring-ttt.interface :refer :all]
            [ttt.board :refer :all]))

(defn parse-params [parameter request]
  (get-in request [:params parameter]))

(defn convert-mode [mode]
  (if (= mode PVC) :pvc :pvp))

(defn convert-difficulty [difficulty]
  (if (= difficulty HARD) :hard :easy))

(defn convert-turn [turn]
  (if (= turn PLAYER1) :player1 :player2))

(defn parse-game-mode [request]
  (convert-mode (parse-params MODE request)))

(defn parse-game-difficulty [request]
  (convert-difficulty (parse-params DIFFICULTY request)))

(defn parse-game-turn [request]
  (convert-turn (parse-params TURN request)))

(defn parse-game-board [request]
  (parse-params BOARD request))

(defn parse-game-move [request]
  (parse-params MOVE request))

(defn parse-new-game-params [request]
  {:board       new-board
   :turn        (parse-game-turn request)
   :mode        (parse-game-mode request)
   :difficulty  (parse-game-difficulty request)})

(defn parse-make-move-params [request]
  {:board       (parse-game-board request)
   :turn        (parse-game-turn request)
   :mode        (parse-game-mode request)
   :move        (parse-game-move request)
   :difficulty  (parse-game-difficulty request)})

(defn new-game [request]
  (start-game (parse-new-game-params request)))

(defn make-move [request]
  (play-game (parse-make-move-params request)))

(defn game-controller [request]
  (let [uri  (get-uri request)
        path (split-uri uri)]
    (cond (= NEW-GAME  (last path)) new-game
          (= MAKE-MOVE (last path)) make-move)))

(defn game? [request]
  (let [uri  (get-uri request)
        path (split-uri uri)]
  (= GAME (first (rest path)))))
