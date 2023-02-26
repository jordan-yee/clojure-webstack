(ns webstack.app-db.initial-values
  "This namespace consolidates all initial values for re-frame's app-db."
  (:require
   [webstack.pages.home.state :as home-state]))

(defn make-initial-values []
  (merge {:pages {:home home-state/initial-values}}))

