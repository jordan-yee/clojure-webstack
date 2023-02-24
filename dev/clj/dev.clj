(ns dev
  "Component lifecycle services."
  (:require
   [clojure.tools.namespace.repl :as tn]
   [taoensso.timbre :as timbre]
   [mount-up.core :as mu]
   [mount.core :as mount]
   [webstack.config :as config :refer [config]]
   [webstack.server]))

;; https://github.com/ptaoussanis/timbre#set-the-minimum-logging-level
;; Set min-level to the highest to keep loggins quiet at the REPL
;; Not sure if this is the right approach
(timbre/set-min-level! :error)

(defn print-mount-actions
  "Based on `mount-up.core/log`, but using println's instead of logging to limit
  noise in REPL."
  [{:keys [name action]}]
  (case action
    :up (println ">> starting.." name)
    :down (println "<< stopping.." name)))

(defn start []
  (mu/on-upndown :info print-mount-actions :before)
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
