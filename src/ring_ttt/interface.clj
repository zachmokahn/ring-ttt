(ns ring-ttt.interface
  (:require [ring-ttt.constants :refer :all]
            [ttt.rules :refer :all]
            [ttt.board :refer :all]
            [ttt.ai :refer :all]))

(declare play-game move-game take-turn)

(defn determine-winner [game-data]
  (let [board (:board game-data)]
    (if (draw? board) DRAW
    (if (win? board :player1) PLAYER1 PLAYER2))))

(defn return-data [board turn]
  {:board board :turn turn})

(defn game-over-return [game-data]
  (assoc game-data :game-over true
   :winner (determine-winner game-data)))

(defn game-active-return [game-data]
  (assoc game-data :game-over false))

(def game-modes
  { :pvc {:player1 :player
          :player2 :computer}
    :pvp {:player1 :player
          :player2 :player}})

(defn player-type [mode]
  (mode game-modes))

(defn game-evaluation [data]
  (if (game-over? (:board data))
    (game-over-return data)
    (game-active-return data)))

(defn start-game [game-parameters]
  (let [board (:board game-parameters)
        turn  (:turn  game-parameters)
        mode  (:mode  game-parameters)]
    (if (= :computer (turn (player-type mode)))
          (play-game game-parameters)
          (return-data board turn))))

(defn play-game [game-parameters]
  (game-evaluation (move-game game-parameters)))

(defn take-turn [turn board index mode]
  (if (= :computer (turn (player-type mode)))
        (move board (best-move board turn) turn)
        (move board (read-string    index) turn)))

(defn move-game [game-parameters]
  (let [board     (:board game-parameters)
        mode      (:mode  game-parameters)
        turn      (:turn  game-parameters)
        index     (:move  game-parameters)
        opponent  (change-turn turn)
        next-board (take-turn turn board index mode)]
        (if (= :computer (opponent (player-type mode)))
          (return-data (take-turn opponent next-board "_" mode) turn)
          (return-data next-board opponent))))
