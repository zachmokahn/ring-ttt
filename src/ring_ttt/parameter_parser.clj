(ns ring-ttt.parameter-parser
  (:require [ring-ttt.constants :refer :all]))

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
