(ns webstack.handlers.page-home-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :as mock]
   [webstack.handlers.page-home :as page-home]))

(deftest handler-test
  (let [request (mock/request :get  "/")
        response (page-home/handler request)]
    (is (= 200 (:status response))
        "the home page request returns a success response")
    (is (and (= "text/html" (get-in response [:headers "Content-Type"]))
             (string? (:body response)))
        "the home page request returns an html string")
    ;; TODO:
    #_(is (string? (:body response))
          "the home page request returns valid html"))
  (is (= (page-home/handler (mock/request :get  "/"))
         (page-home/handler (mock/request :get  "")))
      "home page is loaded with or without the trailing slash"))
