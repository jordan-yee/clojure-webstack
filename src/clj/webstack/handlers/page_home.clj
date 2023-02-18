(ns webstack.handlers.page-home
  "The handler that returns the home page."
  (:require
   [hiccup.page :as h]
   [ring.util.response :as response]))

(defn loading-indicator
  "Returns the Hiccup data to render a loading indicator."
  []
  ;; TODO: Center content on page
  [:div "Loading..."])

(def home-page
  (h/html5
   [:head
    ;; TODO: Register style sheets here:
    (h/include-css "")]
   [:body
    [:div#root
     [:h1 "Home"] ;; TODO: Remove this once the client-side infrastructure is set up.
     (loading-indicator)]
    ;; TODO: Register CDN-based JS libs here:
    (h/include-js "")]))

(defn handler [_]
  (-> home-page
      (response/response)
      (response/content-type "text/html")))

(def route {:name ::home
            :get handler})
