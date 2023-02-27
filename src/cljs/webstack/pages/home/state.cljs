(ns webstack.pages.home.state
  "Re-frame registrations related to home page-specific state."
  (:require
   [re-frame.core :as rf]
   [webstack.app-db.root-subs :as root-subs]
   [webstack.re-frame-helpers :as rfh]
   [webstack.utils :as utils]))

(def initial-values {:content "Are you new here?"})

(def root-path [:pages :home])
(def get-in-context (utils/make-get-in-context root-path))
(def assoc-in-context (utils/make-assoc-in-context root-path))

(rf/reg-sub
 ::context
 :<- [::root-subs/pages]
 (fn [pages _]
   (get pages :home)))

(rf/reg-sub
 ::welcome-message
 :<- [::context]
 (fn [context _]
   (get context :content)))

(rfh/reg-event-db
 ::update-welcome-message
 (fn [db [_ new-message]]
   (assoc-in-context db :content new-message)))
