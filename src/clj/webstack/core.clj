(ns webstack.core
  "This namespace is the entry point into the program. It should contain the
  top-level setup for the build, with specialized application functionality
  being required from elsewhere."
  (:require
   [webstack.server :as server])
  (:gen-class))

(defn start []
  {:web-server (server/start-server)})

(def app (atom nil))

(defn stop [app-instance]
  (server/stop-server (:web-server app-instance))
  :webstack-stopped)
(comment
  (stop app))

#_{:clj-kondo/ignore [:unused-binding]}
(defn -main [& args]
  (println "Starting webstack....")
  (reset! app (start))
  :webstack-started)
