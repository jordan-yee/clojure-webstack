(ns webstack.pages.home.state
  "Re-frame registrations related to home page-specific state."
  (:require
   [webstack.re-frame-helpers :as rfh]
   [re-frame.core :as rf]))

(def initial-values {:content "Welcome to Webstack!"})

(def root-path [:pages :home])
(def get-in-context (rfh/make-get-in-context root-path))
(def assoc-in-context (rfh/make-assoc-in-context root-path))

(rf/reg-sub
 ::welcome-message
 (fn [db _]
   (get-in-context db :content)))

(rfh/reg-event-db
 ::update-welcome-message
 (fn [db [_ new-message]]
   (assoc-in-context db :content new-message)))

(rf/reg-fx
 ::alert
 (fn [message]
   (js/alert message)))

(rfh/reg-event-fx
 ::display-welcome-message-alert
 (fn [_ [_ new-message]]
   {::alert new-message}))
