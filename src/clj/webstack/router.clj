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

(defn- make-page-middleware
  "Composes a collection of middleware to be applied to page requests using the
  supplied options."
  [opts]
  (let [{:keys [env]} opts]
    [[middleware/wrap-defaults (if (= :dev env)
                                 middleware/site-defaults
                                 middleware/secure-site-defaults)]]))

(defn- make-api-middleware
  "Composes a collection of middleware to be applied to API requests using the
  supplied options."
  [opts]
  (let [{:keys [env]} opts]
    [[middleware/wrap-defaults (if (= :dev env)
                                 middleware/api-defaults
                                 middleware/secure-api-defaults)]
     middleware/wrap-json-response]))

(defn- make-global-middleware
  "Composes a collection of middleware to be applied to all requests using the
  supplied options."
  [_opts]
  [middleware/wrap-request-observer])

(defn- make-page-routes
  "Composes page route definitions using the supplied options."
  [opts]
  ["/" {:middleware (make-page-middleware opts)}
   ["" page-home/route]])

(defn- make-api-routes
  "Composes API route definitions using the supplied options."
  [opts]
  ["/api/v1" {:middleware (make-api-middleware opts)}
   ["/data" api-data/route]])

(defn- make-all-routes
  "Composes all route definitions using the supplied options."
  [opts]
  [(make-page-routes opts)
   (make-api-routes opts)])

(defn make-app-handler
  "Returns a Reitit ring-handler instance, which should be passed to `run-jetty`
  when starting the web server.

  `opts` - a map of options used to make the top-level ring handler:
  - `:env` - If set to :dev, then non-secure or dev-only middleware is applied.
    A secure, production-oriented middleware configuration is applied by default."
  [& [opts]]
  (ring/ring-handler
   (ring/router (make-all-routes opts))
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    default/handler)
   {:middleware (make-global-middleware opts)}))
