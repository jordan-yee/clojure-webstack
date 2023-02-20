(ns webstack.router
  "Route definitions for the web server."
  (:require
   [reitit.ring :as ring]
   [webstack.middleware :as middleware]
   [webstack.handlers.api-data :as api-data]
   [webstack.handlers.default :as default]
   [webstack.handlers.page-home :as page-home]))

(def page-routes
  [["/" page-home/route {:middleware [[(middleware/get-wrap-defaults :page)]]}]
   ["/api/v1" {:middleware [[(middleware/get-wrap-defaults :api)
                             middleware/wrap-json-response]]}
    ["/data" api-data/route]]])

(def app (ring/ring-handler
          (ring/router page-routes)
          default/handler
          {:middleware [[middleware/wrap-request-observer]]}))
