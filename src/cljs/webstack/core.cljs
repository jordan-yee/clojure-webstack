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

(defn nav-link [label url]
  (let [current-path (<sub [::router-state/current-path])
        style {:margin-left "0.25em"}]
    (if (= current-path url)
      [:span {:style style} label]
      [:a {:href url, :style style} label])))

(defn app-nav []
  [:nav
   [nav-link "Home" (rfe/href :webstack.pages.home.route/home)]
   [nav-link "Other" (rfe/href :webstack.router.routes/other)]])

(defn app-container []
  (let [current-page (<sub [::router-state/current-page])]
    [:div {:style {:margin "1em"}}
     [app-nav]
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
