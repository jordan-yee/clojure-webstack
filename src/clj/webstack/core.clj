(ns webstack.core
  "This namespace is the entry point into the program. It should contain the
  top-level setup for the build, with specialized application functionality
  being required from elsewhere."
  (:gen-class))

#_{:clj-kondo/ignore [:unused-binding]}
(defn -main [& args]
  (println "webstack: hello world"))
