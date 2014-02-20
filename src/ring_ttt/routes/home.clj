(ns ring-ttt.routes.home)

(defn home [request]
  {:status 200
   :header {"Content-Type" "text/html"}
   :body "<h1>Unbeatable Tic Tac Toe</h1>"})
