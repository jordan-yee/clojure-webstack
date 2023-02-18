(ns webstack.handlers.page-home
  "The handler that returns the home page."
  (:require
   [ring.util.response :as response]))

(def last-request (atom nil))
(defn handler [request]
  (reset! last-request request)
  (let [body "This is the home page!"]
    (-> body
        (response/response)
        (response/content-type "text/html"))))

(def route {:name ::home
            :get handler})
