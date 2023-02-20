(ns webstack.router
  "Route definitions for the web server."
  (:require
   [reitit.ring :as ring]
   [ring.middleware.defaults :as middleware-defaults]
   [ring.middleware.json :as middleware-json]
   [webstack.handlers.api-data :as api-data]
   [webstack.handlers.default :as default]
   [webstack.handlers.page-home :as page-home]
   [webstack.middleware.request-observer :as request-observer]))

(defn- get-wrap-defaults
  "Returns a middleware function for the target `route-type` based on execution
  environment (dev/prod).

  `route-type` - #{:page :api}

  https://github.com/ring-clojure/ring-defaults"
  [route-type]
  (fn [handler]
    (let [;; TODO: Set this based on env var, or something:
          environment "dev"]
      (middleware-defaults/wrap-defaults
       handler
       (cond
         (and (= "dev" environment) (= :page route-type))
         middleware-defaults/site-defaults

         (and (= "prod" environment) (= :page route-type))
         middleware-defaults/secure-site-defaults

         (and (= "dev" environment) (= :api route-type))
         middleware-defaults/api-defaults

         (and (= "prod" environment) (= :api route-type))
         middleware-defaults/secure-api-defaults

         :else
         (throw (Exception.
                 "Invalid or unrecognized execution environment and route-type set!")))))))

(def page-routes
  [["/" page-home/route {:middleware [[(get-wrap-defaults :page)]]}]
   ["/api/v1" {:middleware [[(get-wrap-defaults :api)
                             middleware-json/wrap-json-response]]}
    ["/data" api-data/route]]])

(def app (ring/ring-handler
          (ring/router page-routes)
          default/handler
          {:middleware [[request-observer/wrap-request-observer]]}))
