(ns webstack.handlers.default
  "The default handler that returns a 404 / not found page response."
  (:require
   [ring.util.response :as response]))

(defn handler [_]
  (let [body "Page not found!"]
    (-> body
        (response/not-found)
        (response/content-type "text/html"))))
