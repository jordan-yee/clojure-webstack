(ns webstack.core
  "This is the entry point into the ClojureScript client application."
  (:require
   [webstack.app-db.initial-values :as initial-values]
   [reagent.dom :as rd]
   [webstack.re-frame-helpers :refer [<sub >evt] :as rfh]
   [re-frame.core :as rf]))

(rf/reg-sub
 ::welcome-message
 (fn [db _]
   (get-in db [:pages :home :content])))

(rfh/reg-event-db
 ::update-welcome-message
 (fn [db [_ new-message]]
   (assoc-in db [:pages :home :content] new-message)))

(rf/reg-fx
 ::alert
 (fn [message]
   (js/alert message)))

(rfh/reg-event-fx
 ::display-welcome-message-alert
 (fn [_ [_ new-message]]
   {::alert new-message}))

(defn home-page []
  (let [content (<sub [::welcome-message])
        welcome-message "Welcome to the updated Webstack!"]
    [:div
     [:h1 "Home Page"]
     [:button
      {:on-click #(>evt [::set-initial-data])}
      "Reset"]
     [:button
      {:on-click #(>evt [::update-welcome-message welcome-message])}
      "Update Message"]
     [:button
      {:on-click #(>evt [::display-welcome-message-alert welcome-message])}
      "Welcome Alert"]
     [:p content]]))

(defn page-container [content]
  [:div {:style {:margin "1em"}}
   [content]])

(rfh/reg-event-db
 ::set-initial-data
 (fn [_ _]
   (initial-values/make-initial-values)))

(defn- ^:dev/after-load mount-app []
  (rd/render [page-container home-page]
             (js/document.getElementById "root")))

(defn- fake-load
  "Wait for 1 second then execute `(f)` to simulate a loading delay."
  [f]
  (js/setTimeout (fn [_] (f)) 1000))

(defn main
  "Entry point for the client program."
  []
  (println "Client started...")
  (rf/dispatch-sync [::set-initial-data])
  (fake-load mount-app))
