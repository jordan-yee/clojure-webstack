(ns webstack.client-manifest-test
  (:require
   [webstack.client-manifest :as cm]
   [clojure.test :refer [deftest is]]))

(deftest get-client-module-filename-test
  (let [mock-client-manifest [{:module-id :main
                               :name :main
                               :output-name "main.js"}]]
    (is (= "main.js" (cm/get-client-module-filename mock-client-manifest))
        "filename is retrieved from client-manifest data")))
