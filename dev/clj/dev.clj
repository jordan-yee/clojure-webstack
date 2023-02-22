(ns dev
  "Component lifecycle services."
  (:require
   [clojure.tools.namespace.repl :as tn]
   [mount.core :as mount]
   [webstack.config :as config :refer [config]]
   [webstack.server]))

(defn start []
  (-> (mount/swap {#'config (config/load-config-dev)})
      (mount/start)))

(defn stop []
  (mount/stop))

(defn refresh []
  (stop)
  (tn/refresh))

(defn refresh-all []
  (stop)
  (tn/refresh-all))

(defn go
  "Start all states defined by defstate (from mount)."
  []
  (start)
  :ready)

(defn reset []
  (stop)
  (tn/refresh :after 'dev/go))
