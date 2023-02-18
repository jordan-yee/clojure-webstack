(ns webstack.test-utils.response
  "Test utilities & helpers for page handlers.")

(defn has-content-type? [content-type response]
  (= content-type (get-in response [:headers "Content-Type"])))

(defn content-type-html? [response]
  (has-content-type? "text/html" response))
