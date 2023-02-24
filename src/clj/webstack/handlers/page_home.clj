(ns webstack.handlers.page-home
  "The handler that returns the home page."
  (:require
   [hiccup.page :as h]
   [webstack.client-manifest :refer [client-manifest] :as cm]
   [ring.util.response :as response]))

(defn render-loading-indicator
  "Returns the Hiccup data to render a loading indicator."
  []
  [:div.flex-center
   [:h1 "Loading..."]])

(defn render-home-page []
  (h/html5
   [:head
    (h/include-css "/css/styles.css")]
   [:body
    [:div.flex-top-level-container
     [:div#root.flex-page-container
      (render-loading-indicator)]]
    (h/include-js (str "/js/" (cm/get-client-module-filename client-manifest)))]))

(defn handler [_]
  (-> (render-home-page)
      (response/response)
      (response/content-type "text/html")))

(def route {:name ::page-home
            :get handler})
