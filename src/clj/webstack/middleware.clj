(ns webstack.middleware
  "This is the consolidated middleware index."
  (:require
   [potemkin                             :as p]
   [ring.middleware.defaults             :as middleware-defaults]
   [ring.middleware.json                 :as middleware-json]
   [webstack.middleware.request-observer :as request-observer]))

(p/import-vars
 [middleware-defaults
  wrap-defaults
  site-defaults secure-site-defaults api-defaults secure-api-defaults]
 [middleware-json wrap-json-response]
 [request-observer wrap-request-observer])
