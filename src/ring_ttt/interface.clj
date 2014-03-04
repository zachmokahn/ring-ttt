(ns ring-ttt.interface)

(defn other-turn [params])

(defn play-game [game-parameters]
  {:board (:board game-parameters)
   :turn  (other-turn game-parameters)})
