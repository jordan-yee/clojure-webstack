(ns webstack.server
  "The entry point and top-level instance management for the web server."
  (:require
   [mount.core :refer [defstate]]
   [ring.adapter.jetty :as jetty]
   [webstack.handlers.page-home :as page-home]
   [webstack.middleware.request-observer :as request-observer]))

;; NOTE: Middleware runs from bottom to top, and if any middleware creates a
;; response, that will short-circuit and the middleware above will not be called.
(def app (-> page-home/handler
             request-observer/wrap-request-observer))

(defn start-server
  "Start the Jetty web server.

  See configuration options here:
  https://ring-clojure.github.io/ring/ring.adapter.jetty.html"
  []
  (println "webstack: starting web server...")
  ;; TODO: print server URL on start
  (jetty/run-jetty app {:port 3000
                        ;; Prevent the server from blocking the thread for RDD
                        :join? false}))

(defn stop-server [web-server]
  (println "webstack: stopping web server...")
  (.stop web-server))

(defstate web-server
  :start (start-server)
  :stop (stop-server web-server))
