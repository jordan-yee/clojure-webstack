(ns webstack.router
  "Client-side routing."
  (:require
   [reitit.frontend.controllers :as rfc]
   [reitit.frontend.easy :as rfe]
   [webstack.re-frame-helpers :as rfh :refer [>evt]]
   [webstack.router.routes :refer [routes]]
   [webstack.router.state :as router-state]))

(defn- update-match [update-fn]
  (>evt [::router-state/update-matched-route update-fn]))

(defn- transition-controllers
  "Effectively calls relevant `start` or `stop` controller functions when
  navigating to a new route. Beyond that, maintains controller identities
  which determines which lifecycle functions should be called."
  [new-match old-match]
  (assoc new-match :controllers
         (rfc/apply-controllers (:controllers old-match) new-match)))

(defn- on-navigate [new-match _history]
  (update-match (fn [old-match]
                  ;; Why is this `when` check needed (it's in examples)?
                  ;; When would `new-match` ever be nil?
                  (when new-match
                    (transition-controllers new-match old-match)))))

(defn init! []
  (rfe/start! routes on-navigate {:use-fragment false}))
