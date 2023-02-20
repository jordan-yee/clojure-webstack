(ns webstack.middleware-test
  (:require
   [clojure.test :refer :all]
   [reitit.ring :as ring]
   [ring.mock.request :as mock]
   [webstack.middleware :as middleware]))

;; TODO: Add tests with requests against `router/app` to test external
;; middleware as each feature is actually utilized.

(deftest params-test
  (let [request (mock/request :get "/page?contains-params=true")
        middleware-processed-request (atom nil)
        handler (fn [request]
                  (reset! middleware-processed-request request))
        router (ring/ring-handler
                (ring/router
                 ["/page" {:get handler
                           :middleware [(middleware/get-wrap-defaults :page)]}]))]
    (router request)
    (is (contains? (:params @middleware-processed-request) :contains-params))))
