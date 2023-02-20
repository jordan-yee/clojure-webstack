(ns webstack.middleware
  "This is the consolidated middleware index."
  (:require
   [potemkin                             :as p]
   [ring.middleware.defaults             :as middleware-defaults]
   [ring.middleware.json                 :as middleware-json]
   [webstack.middleware.request-observer :as request-observer]))

(defn get-wrap-defaults
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

(p/import-vars
 [middleware-json wrap-json-response]
 [request-observer wrap-request-observer])
