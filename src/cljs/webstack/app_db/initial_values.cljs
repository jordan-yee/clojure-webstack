(ns webstack.app-db.initial-values
  "This namespace consolidates all initial values for re-frame's app-db."
  (:require
   [webstack.pages.home.view :refer [home-page]]
   [webstack.pages.home.state :as home-state]))

(defn make-initial-values []
  (merge {:matched-route {:data {:view home-page}}}
         {:pages {:home home-state/initial-values}}))

