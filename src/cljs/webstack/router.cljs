(ns webstack.router
  "Client-side routing."
  (:require
   [reitit.frontend :as rf]
   [reitit.frontend.controllers :as rfc]
   [reitit.frontend.easy :as rfe]
   [webstack.router.state :as router-state]
   [webstack.pages.home.view :refer [home-page]]
   [webstack.re-frame-helpers :as rfh :refer [>evt]]))

(def routes
  (rf/router
   ["/"
    ["" {:name ::home
         :view home-page
         :controllers [{:start (fn [_] (js/console.log :start "home-page start"))
                        :stop (fn [_] (js/console.log :start "home-page stop"))}]}]
    ["other" {:name ::other
              :view (fn [] [:div "other"])
              :controllers [{:start (fn [_] (js/console.log :start "other-page start"))
                             :stop (fn [_] (js/console.log :start "other-page stop"))}]}]]))

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
