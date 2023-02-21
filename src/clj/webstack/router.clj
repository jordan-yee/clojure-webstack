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
  [["/" {:middleware [[middleware/wrap-defaults middleware/site-defaults]]}
    ["" page-home/route]]])

(def api-routes
  [["/api/v1" {:middleware [[middleware/wrap-defaults middleware/api-defaults]
                            middleware/wrap-json-response]}
    ["/data" api-data/route]]])

(def all-routes [page-routes api-routes])

(def app (ring/ring-handler
          (ring/router all-routes)
          (ring/routes
           (ring/redirect-trailing-slash-handler)
           default/handler)
          {:middleware [middleware/wrap-request-observer]}))
