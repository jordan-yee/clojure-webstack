(ns user
  "Initial REPL namespace. Contains development-time helpers & utilities."
  #_{:clj-kondo/ignore [:unused-namespace :unused-referred-var :refer-all]}
  (:require
   [clojure.repl :refer :all]
   [dev :refer [go reset]]
   [puget.printer :as puget]
   [portal.api :as portal]))

(defn start-portal []
  (portal/open)
  (portal/tap))

(defn stop-portal []
  (portal/clear)
  (portal/close))

(defn pprint-last
  "Print the value last returned at the REPL, formatted & colorized w/ puget."
  []
  (puget/cprint *1))

(defn tap-last
  "Tap the last value returned at the REPL, presumably to view in Portal."
  []
  (tap> *1))
