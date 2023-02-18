(ns webstack.handlers.page-home-test
  #_{:clj-kondo/ignore [:refer-all]}
  (:require
   [clojure.test :refer :all]
   [webstack.handlers.page-home :as page-home]))

(deftest handler-test
  (let [request {}
        expected-response {:status 200
                           :headers {"Content-Type" "text/html"}
                           :body "This is the home page!"}
        actual-response (page-home/handler request)]
    (is (= expected-response actual-response)
        "expected static response is returned")))
