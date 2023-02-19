(ns webstack.handlers.api-data-test
  (:require
   [cheshire.core :as json]
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [ring.util.http-predicates :as predicates]
   [webstack.router :as router]
   [webstack.test-utils.response :as response]
   [webstack.handlers.api-data :as api-data]))

(deftest handler-test
  (let [request (mock/request :get "/api/v1/data")
        response (api-data/handler request)]
    ;; NOTE: This assertion should really be handled by a spec/schema
    (is (map? (:body response))
        "returns a map of data")))

(defn- is-valid-json? [data]
  (try
    (json/parse-string data)
    true
    (catch Exception e
      (println "JSON parse error:")
      (println (.getMessage e))
      false)))

(deftest route-test
  (let [request (mock/request :get "/api/v1/data")
        response (router/app request)]
    (is (predicates/ok? response)
        "returns a success response")
    (is (response/content-type-json? response)
        "returns content-type json")
    (is (is-valid-json? (:body response))
        "returns data as a valid json string")))
