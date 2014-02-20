(ns ring-ttt.routes.four-oh-four)

  (defn four-oh-four [_]
    {:status 404
     :headers {"Content-Type" "text/html"}
     :body "<h1>404 Errror: Page not Found</h1>"})
