(ns webstack.handlers.api-data-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [ring.util.http-predicates :as predicates]
   [webstack.handlers.api-data :as api-data]
   [webstack.router :as router]
   [webstack.test-utils.response :as response]))

(deftest handler-test
  (let [request (mock/request :get "/api/v1/data")
        response (api-data/handler request)]
    ;; NOTE: This assertion should really be handled by a spec/schema
    (is (map? (:body response))
        "returns a map of data")))

(deftest route-test
  (let [request (mock/request :get "/api/v1/data")
        response ((router/make-app-handler {:env :dev}) request)]
    (is (predicates/ok? response)
        "returns a success response")
    (is (response/content-type-json? response)
        "returns content-type json")
    (is (response/is-body-json? response)
        "returns data as a valid json string")))
