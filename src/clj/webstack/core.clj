(ns webstack.core
  "This namespace is the entry point into the program. It should contain the
  top-level setup for the build, with specialized application functionality
  being required from elsewhere."
  (:require
   [mount.core :as mount]
   [webstack.server])
  (:gen-class))

#_{:clj-kondo/ignore [:unused-binding]}
(defn -main [& args]
  (println "Starting webstack....")
  (mount/start))
