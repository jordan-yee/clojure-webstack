(ns webstack.core
  "This is the entry point into the ClojureScript client application."
  (:require
   [reagent.dom :as rd]
   [re-frame.core :as rf]))

(rf/reg-event-db
 ::set-initial-data
 (fn [db [_ initial-data]]
   (when-not (empty? db)
     (js/console.warn "`app-db` is not empty, but is being initialized with new data")
     (js/console.warn "`app-db` value being overwritten:" db))
   initial-data))

(rf/reg-sub
 ::home-page-content
 (fn [db _]
   (get-in db [:home-page :content])))

(rf/reg-event-db
 ::update-welcome-message
 (fn [db [_ new-message]]
   (assoc-in db [:home-page :content] new-message)))

(defn home-page []
  (let [content @(rf/subscribe [::home-page-content])]
    [:div
     [:h1 "Home Page"]
     [:button
      {:on-click #(rf/dispatch [::update-welcome-message "Welcome to the updated Webstack!"])}
      "Update"]
     [:p content]]))

(defn page-container [content]
  [:div {:style {:margin "1em"}}
   [content]])

(defn mount-app []
  (rd/render [page-container home-page]
             (js/document.getElementById "root")))

(defn fake-load
  "Wait for 1 second then execute `(f)` to simulate a loading delay."
  [f]
  (js/setTimeout (fn [_] (f)) 1000))

(defn ^:export main []
  (println "Client started...")
  (rf/dispatch-sync [::set-initial-data {:home-page
                                         {:content "Welcome to Webstack!"}}])
  (fake-load mount-app))

(main)
