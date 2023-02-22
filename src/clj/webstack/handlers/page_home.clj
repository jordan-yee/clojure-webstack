(ns webstack.handlers.page-home
  "The handler that returns the home page."
  (:require
   [hiccup.page :as h]
   [ring.util.response :as response]))

(defn loading-indicator
  "Returns the Hiccup data to render a loading indicator."
  []
  [:div.flex-center
   [:h1 "Loading..."]])

(def home-page
  (h/html5
   [:head
    (h/include-css "/css/styles.css")]
   [:body
    [:div.flex-top-level-container
     [:div#root.flex-page-container
      (loading-indicator)]]
    (h/include-js "/js/main.js")]))

(defn handler [_]
  (-> home-page
      (response/response)
      (response/content-type "text/html")))

(def route {:name ::page-home
            :get handler})
