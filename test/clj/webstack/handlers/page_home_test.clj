(ns webstack.handlers.page-home-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [ring.util.http-predicates :as predicates]
   [webstack.handlers.page-home :as page-home]
   [webstack.router :as router]
   [webstack.test-utils.response :as response]))

(deftest handler-test
  (let [request (mock/request :get  "/")
        response (page-home/handler request)]
    (is (predicates/ok? response)
        "the home page request returns a success response")
    (is (response/content-type-html? response)
        "the home page request returns content-type html")
    (is (string? (:body response))
        "the home page request returns an html string")))

(deftest route-test
  (let [trailing-slash-response (router/app (mock/request :get "/"))
        no-trailing-slash-response (router/app (mock/request :get ""))]
    (is (= (response/remove-set-cookie-header trailing-slash-response)
           (response/remove-set-cookie-header no-trailing-slash-response))
        "home page is returned with or without the trailing slash")))
