(ns webstack.router.state
  "Re-frame registrations related to router state."
  (:require
   [re-frame.core :as rf]
   [webstack.app-db.root-subs :as root-subs]
   [webstack.re-frame-helpers :as rfh]
   [webstack.utils :as utils]))

(defn placeholder-page
  "Component initially stored in app-db before a route is matched. This
  shouldn't ever be seen in practice."
  []
  [:div "Matching route..."])

(def initial-values {:matched-route {:data {:view placeholder-page}}})

(def root-path [:router])
(def update-in-context (utils/make-update-in-context root-path))

(rfh/reg-event-db
 ::update-matched-route
 (fn [db [_ update-fn]]
   (update-in-context db :matched-route update-fn)))

(rf/reg-sub
 ::current-page
 :<- [::root-subs/router]
 (fn [router _]
   (get-in router [:matched-route :data :view])))

(rf/reg-sub
 ::current-path
 :<- [::root-subs/router]
 (fn [router _]
   (get-in router [:matched-route :path])))
