(ns webstack.core
  "This is the entry point into the ClojureScript client application."
  (:require
   [re-frame.core :as rf]
   [reagent.dom :as rd]
   [reitit.frontend.easy :as rfe]
   [webstack.app-db.initial-values :as initial-values]
   [webstack.re-frame-helpers :as rfh :refer [<sub]]
   [webstack.router.state :as router-state]
   [webstack.router :as router]))

(defn app-container []
  (let [current-page (<sub [::router-state/current-page])]
    [:div {:style {:margin "1em"}}
     ;; TODO: implement basic html nav
     [:a {:href (rfe/href :webstack.pages.home.route/home)}
      "home"]
     [:span " "]
     [:a {:href (rfe/href :webstack.router.routes/other)}
      "other"]
     [current-page]]))

(rfh/reg-event-db
 ::set-initial-data
 (fn [_ _]
   (initial-values/make-initial-values)))

(defn- ^:dev/after-load mount-app []
  (rd/render [app-container]
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
  (router/init!)
  (fake-load mount-app))
