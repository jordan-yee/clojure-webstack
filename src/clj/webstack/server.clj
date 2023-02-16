(ns webstack.server
  "The entry point and top-level instance management for the web server."
  (:require
   [mount.core :refer [defstate]]))

(defn start-server []
  (println "webstack: starting server..."))

(defn stop-server [_server]
  (println "webstack: stop server..."))

(defstate web-server
  :start (start-server)
  :stop (stop-server web-server))
