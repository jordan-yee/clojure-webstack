(ns webstack.test-utils.response
  "Test utilities & helpers for page handlers."
  (:require
   [cheshire.core :as json]))

;; NOTE: Code added here is considered to be "tested in production", meaning
;; that we're not unit testing it directly, but expect any issues to become
;; apparent during it's actualy use (in the tests that use it).

(defn- has-content-type? [content-type response]
  (= content-type (get-in response [:headers "Content-Type"])))

(defn content-type-html? [response]
  (has-content-type? "text/html" response))

(defn content-type-json? [response]
  (has-content-type? "application/json" response))

(defn- is-valid-json? [data]
  (try
    (json/parse-string data)
    true
    (catch Exception e
      (println "JSON parse error:")
      (println (.getMessage e))
      false)))

(defn is-body-json? [response]
  (is-valid-json? (:body response)))
