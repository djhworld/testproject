(ns testproject.server
  (:use [noir.fetch.remotes])
  (:require [noir.server :as server]))

(server/load-views "src/testproject/views/")
(server/add-middleware wrap-remotes)

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'testproject})))

