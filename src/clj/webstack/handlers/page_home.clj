(ns webstack.handlers.page-home
  "The handler that returns the home page."
  (:require
   [hiccup.page :as h]
   [ring.util.response :as response]))

(defn loading-indicator
  "Returns the Hiccup data to render a loading indicator."
  []
  ;; TODO: Center content on page
  [:div.flex-center
   [:h1 "Loading..."]])

(def home-page
  (h/html5
   [:head
    ;; TODO: Register style sheets here:
    (h/include-css "/css/styles.css")]
   [:body
    [:div.flex-top-level-container
     [:div#root.flex-page-container
      (loading-indicator)]]
    ;; TODO: Register JavaScript files here:
    (h/include-js "")]))

(defn handler [_]
  (-> home-page
      (response/response)
      (response/content-type "text/html")))

(def route {:name ::page-home
            :get handler})
