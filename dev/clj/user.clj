(ns user
  (:require
   [webstack.core :as core]
   [clojure.tools.namespace.repl :refer [refresh]]
   [portal.api :as portal]))

(defn start-portal []
  (portal/open)
  (portal/tap))

;; TODO: Get things set up for a refresh workflow:
;; https://github.com/clojure/tools.namespace#reloading-code-preparing-your-application
(defn restart []
  (refresh))
