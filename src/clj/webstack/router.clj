(ns webstack.router
  "Route definitions for the web server."
  (:require
   [reitit.ring :as ring]
   [webstack.middleware.request-observer :as request-observer]
   [webstack.handlers.page-home :as page-home]))

;; NOTE: Middleware runs from bottom to top, and if any middleware creates a
;; response, that will short-circuit and the middleware above will not be called.
(def routes
  [["/" {:middleware [[request-observer/wrap-request-observer]]}
    ["" {:name ::home
         :get page-home/handler}]]])

(def app (ring/ring-handler
          (ring/router routes)))
