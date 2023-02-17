(ns webstack.handlers.page-home
  "The handler that returns the home page.")

(def last-request (atom nil))
(defn handler [request]
  (reset! last-request request)
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "This is the home page!"})
