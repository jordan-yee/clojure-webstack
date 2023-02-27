(ns webstack.router
  "Client-side routing."
  (:require
   [re-frame.core :as re-frame]
   [reitit.frontend :as rf]
   [reitit.frontend.easy :as rfe]
   [reitit.frontend.controllers :as rfc]
   [webstack.pages.home.view :refer [home-page]]
   [webstack.re-frame-helpers :as rfh :refer [>evt]]))

(rfh/reg-event-db
 ::update-matched-route
 (fn [db [_ update-fn]]
   (update db :matched-route update-fn)))

(re-frame/reg-sub
 ::current-page
 (fn [db _]
   (get-in db [:matched-route :data :view])))

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
  (>evt [::update-matched-route update-fn]))

;; TODO: is this actually an appropriate name for this function?
;; TODO: what is this really doing?
;; TODO: understand controllers better
(defn- transition-controllers [new-match old-match]
  (assoc new-match :controllers
         (rfc/apply-controllers (:controllers old-match) new-match)))

(defn- on-navigate [new-match _history]
  #_(js/console.log "webstack.router/on-navigate called:")
  #_(js/console.log "new-match" new-match)
  #_(js/console.log "history" _history)
  (update-match (fn [old-match]
                  ;; TODO: why is this when check needed?
                  ;; TODO: when would new-match ever be nil (or falsey)?
                  (when new-match
                    (transition-controllers new-match old-match)))))

(defn init! []
  (rfe/start! routes on-navigate {:use-fragment false}))
