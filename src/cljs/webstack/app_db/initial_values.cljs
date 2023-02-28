(ns webstack.app-db.initial-values
  "This namespace consolidates all initial values for re-frame's app-db."
  (:require
   [webstack.pages.home.state :as home-state]
   [webstack.router.state :as router-state]))

(defn make-initial-values []
  (merge {:router router-state/initial-values}
         {:pages {:home home-state/initial-values}}))

