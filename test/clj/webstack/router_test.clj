(ns webstack.router-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [webstack.router :as router]))

;; TODO: Add tests with requests against `router/app` to test external
;; middleware as each feature is actually utilized.

(deftest default-route-test
  (let [bad-request (mock/request :get "/page-that-doesnt-exist")
        response ((router/make-app-handler) bad-request)]
    (is (= 404 (:status response))
        "requesting a non-existent page returns a 404")))
