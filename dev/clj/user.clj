(ns user
  "Initial REPL namespace. Contains development-time helpers & utilities."
  #_{:clj-kondo/ignore [:unused-namespace :unused-referred-var :refer-all]}
  (:require
   [clojure.repl :refer :all]
   [dev :refer [go reset]]
   [portal.api :as portal]))

(defn start-portal []
  (portal/open)
  (portal/tap))

(defn stop-portal []
  (portal/clear)
  (portal/close))
