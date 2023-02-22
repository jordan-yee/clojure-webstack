(ns webstack.router-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [ring.util.http-predicates :as predicates]
   [webstack.router :as router]
   [webstack.test-utils.response :as response]))

(deftest default-route-test
  (let [bad-request (mock/request :get "/page-that-doesnt-exist")
        response ((router/make-app-handler) bad-request)]
    (is (= 404 (:status response))
        "requesting a non-existent page returns a 404")))

(deftest resource-route-test
  (testing "request for static asset"
    (let [request (mock/request :get "/css/styles.css")
          response ((router/make-app-handler {:env :dev}) request)]
      (is (predicates/ok? response)
          "returns success response")
      (is (response/content-type-css? response)
          "returns expected content type for resource"))))
