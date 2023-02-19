(ns webstack.handlers.api-data
  "API handler that returns data."
  (:require
   [ring.util.response :as response]))

(defn handler [request]
  (-> (response/response {:data "example"})
      (response/content-type "application/json")))

(def route {:name ::api-data
            :get handler})
