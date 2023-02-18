(ns webstack.router
  "Route definitions for the web server."
  (:require
   [reitit.ring :as ring]
   [webstack.middleware.request-observer :as request-observer]
   [webstack.handlers.page-home :as page-home]))

(def page-routes
  [["/" page-home/route]])

(def app (ring/ring-handler
          (ring/router routes)))
