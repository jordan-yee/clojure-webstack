(ns webstack.router
  "Route definitions for the web server."
  (:require
   [reitit.ring :as ring]
   [webstack.middleware :as middleware]
   [webstack.handlers.api-data :as api-data]
   [webstack.handlers.default :as default]
   [webstack.handlers.page-home :as page-home]))

;; TODO: Best way to define differences between route definitions in during
;; development/testing & production? (This probably just involves middleware.)

(def page-routes
  [["/" {:middleware [(middleware/get-wrap-defaults :page)]}
    ["" page-home/route]]
   ["/api/v1" {:middleware [(middleware/get-wrap-defaults :api)
                            middleware/wrap-json-response]}
    ["/data" api-data/route]]])

(def app (ring/ring-handler
          (ring/router page-routes)
          (ring/routes
           (ring/redirect-trailing-slash-handler)
           default/handler)
          {:middleware [middleware/wrap-request-observer]}))
