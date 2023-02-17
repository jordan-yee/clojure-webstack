(ns webstack.server
  "The entry point and top-level instance management for the web server."
  (:require
   [webstack.handlers.page-home :as page-home]
   [mount.core :refer [defstate]]
   [ring.adapter.jetty :as jetty]))

(defn start-server
  "Start the Jetty web server.

  See configuration options here:
  https://ring-clojure.github.io/ring/ring.adapter.jetty.html"
  []
  (println "webstack: starting web server...")
  ;; TODO: print server URL on start
  (jetty/run-jetty page-home/handler
                   {:port 3000
                    ;; Prevent the server from blocking the thread for RDD
                    :join? false}))

(defn stop-server [web-server]
  (println "webstack: stopping web server...")
  (.stop web-server))

(defstate web-server
  :start (start-server)
  :stop (stop-server web-server))
