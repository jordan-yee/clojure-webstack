(ns webstack.router
  "Route definitions for the web server."
  (:require
   [reitit.ring :as ring]
   [webstack.handlers.api-data :as api-data]
   [webstack.handlers.default :as default]
   [webstack.handlers.page-home :as page-home]
   [webstack.middleware.request-observer :as request-observer]))

(def page-routes
  [["/" page-home/route]
   ["/api/v1" {:middleware [[]]}
    ["/data" api-data/route]]])

(def app (ring/ring-handler
          (ring/router page-routes)
          default/handler
          {:middleware [[request-observer/wrap-request-observer]]}))
