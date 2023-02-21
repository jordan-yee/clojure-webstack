(ns webstack.middleware-test
  (:require
   [clojure.test :refer :all]
   [reitit.ring :as ring]
   [ring.mock.request :as mock]
   [webstack.middleware :as middleware]))

;; TODO: Add tests for each registered external middleware as their features are
;; actually utilized/required.

(deftest params-test
  (let [request (mock/request :get "/page?contains-params=true")
        middleware-processed-request (atom nil)
        handler (fn [request]
                  (reset! middleware-processed-request request))
        router (ring/ring-handler
                (ring/router
                 ["/page" {:get handler
                           :middleware [[middleware/wrap-defaults middleware/site-defaults]]}]))]
    (router request)
    (is (contains? (:params @middleware-processed-request) :contains-params)
        "query params are extracted & keywordized into :params in request map")))
