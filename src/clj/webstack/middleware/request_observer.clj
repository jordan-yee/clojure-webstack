(ns webstack.middleware.request-observer
  "Middleware for observing incoming requests. These can be logged or otherwise
  captured for debugging, metrics, or other observability concerns.")

;; TODO: Turn this into a circular queue by default? (keep up to N requests)
(def last-request (atom nil))
(defn wrap-request-observer
  "Observes incoming requests by [saving the latest request to an atom]."
  [handler]
  (fn [request]
    (reset! last-request request)
    (handler request)))
