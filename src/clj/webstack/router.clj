(ns webstack.router
  "Route definitions for the web server."
  (:require
   [reitit.ring :as ring]
   [ring.middleware.json :as middleware-json]
   [webstack.handlers.api-data :as api-data]
   [webstack.handlers.default :as default]
   [webstack.handlers.page-home :as page-home]
   [webstack.middleware.request-observer :as request-observer]))

(def page-routes
  [["/" page-home/route]
   ["/api/v1" {:middleware [[middleware-json/wrap-json-response]]}
    ["/data" api-data/route]]])

(def app (ring/ring-handler
          (ring/router page-routes)
          default/handler
          {:middleware [[request-observer/wrap-request-observer]]}))
