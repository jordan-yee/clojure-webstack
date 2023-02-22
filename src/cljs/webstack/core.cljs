(ns webstack.core
  "This is the entry point into the ClojureScript client application."
  (:require
   [reagent.dom :as rd]))

(defn home-page []
  [:div
   [:h1 "Home Page!"]
   [:p "This is the home page!"]])

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

(defn init []
  (println "Client started...")
  (fake-load mount-app))

(init)
