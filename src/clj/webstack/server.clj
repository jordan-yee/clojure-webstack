(ns webstack.server
  "The entry point and top-level instance management for the web server."
  (:require
   [mount.core :refer [defstate]]
   [ring.adapter.jetty :as jetty]
   [webstack.config :refer [config]]
   [webstack.router :as router]))

(defn- start-server
  "Start the Jetty web server. Using the configuration defined by the `:jetty`
  key from the `config` component."
  []
  (jetty/run-jetty (router/make-app-handler (:router config))
                   (:jetty config)))

(defn- stop-server [web-server]
  (.stop web-server))

(defstate web-server
  "The Jetty web server."
  :start (start-server)
  :stop (stop-server web-server))
