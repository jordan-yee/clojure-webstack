(ns webstack.core
  "This is the entry point into the ClojureScript client application."
  (:require
   [re-frame.core :as rf]
   [reagent.dom :as rd]
   [webstack.app-db.initial-values :as initial-values]
   [webstack.pages.home.view :refer [home-page]]
   [webstack.re-frame-helpers :as rfh]))

;; TODO: Configure client-side routing

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
