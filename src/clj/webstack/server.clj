(ns webstack.server
  "The entry point and top-level instance management for the web server."
  (:require
   [mount.core :refer [defstate]]
   [ring.adapter.jetty :as jetty]
   [webstack.router :as router]))

(defn start-server
  "Start the Jetty web server.

  `opts` - a map of options used to make the top-level ring handler:
  - `:env` - If set to :dev, then non-secure or dev-only middleware is applied.
    A secure, production-oriented middleware configuration is applied by default.

  See configuration options here:
  https://ring-clojure.github.io/ring/ring.adapter.jetty.html"
  [& [opts]]
  (println "webstack: starting web server...")
  ;; TODO: print server URL on start
  ;; To pass app as a var or not:
  ;; https://groups.google.com/g/clojure/c/tZpNp0rEBKQ/m/D3XPv94EZw4J?pli=1
  ;; It has to do with reloadability--a var will be deref'd on each request.
  ;; Since we we're using tools.namespace/refresh we'll proceed without the var
  ;; wrapper to avoid the probably negligible performance hit.
  (let [{:keys [env]} opts]
    (jetty/run-jetty (router/make-app-handler opts)
                     (if (= :dev env)
                       {:port 3000
                        ;; Prevent the server from blocking the thread for RDD
                        :join? false}
                       ;; Use production config by default
                       {}))))

(defn stop-server [web-server]
  (println "webstack: stopping web server...")
  (.stop web-server))

(defstate web-server
  :start (start-server)
  :stop (stop-server web-server))

(def web-server-dev
  "During development, use this definition for the `web-server` state:
  ```
  (mount/start-with-states {#'server/web-server server/web-server-dev})
  ```"
  {:start #(start-server {:env :dev})
   :stop #(stop-server web-server)})
