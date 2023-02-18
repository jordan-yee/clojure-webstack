(ns webstack.router-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [webstack.router :as router]))

(deftest default-route-test
  (let [bad-request (mock/request :get "/page-that-doesnt-exist")
        response (router/app bad-request)]
    (is (= 404 (:status response))
        "requesting a non-existent page returns a 404")))
