(ns webstack.handlers.page-home-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [ring.util.http-predicates :as predicates]
   [webstack.test-utils.response :as response]
   [webstack.handlers.page-home :as page-home]))

(deftest handler-test
  (let [request (mock/request :get  "/")
        response (page-home/handler request)]
    (is (predicates/ok? response)
        "the home page request returns a success response")
    (is (and (response/content-type-html? response)
             (string? (:body response)))
        "the home page request returns an html string"))
  (is (= (page-home/handler (mock/request :get  "/"))
         (page-home/handler (mock/request :get  "")))
      "home page is loaded with or without the trailing slash"))
