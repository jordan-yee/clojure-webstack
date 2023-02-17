(ns webstack.database
  "The top-level connection management for the database."
  (:require
   [mount.core :refer [defstate]]))

(defn start-db []
  (println "webstack: starting database connection..."))

(defn stop-db [_db]
  (println "webstack: stopping database connection..."))

(defstate db
  :start (start-db)
  :stop (stop-db db))
