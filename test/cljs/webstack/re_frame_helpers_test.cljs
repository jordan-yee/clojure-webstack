(ns webstack.re-frame-helpers-test
  (:require
   [webstack.re-frame-helpers :as rfh]
   [cljs.test :refer-macros [deftest is testing]]))

(deftest make-get-in-context-test
  (testing "constructed function returns a value from the defined context root"
    (let [mock-app-db {:root {:target-context {:context-val "test-val"
                                               :nested-context
                                               {:nested-val "nested-test-val"}}}}
          get-in-context (rfh/make-get-in-context [:root :target-context])]
      (is (= "test-val" (get-in-context mock-app-db [:context-val]))
          "constructed function accepts a vector path")
      (is (= "nested-test-val" (get-in-context mock-app-db [:nested-context :nested-val]))
          "constructed function can retrieve nested values")
      (is (= "test-val" (get-in-context mock-app-db :context-val))
          "constructed function accepts a keyword"))))

(deftest make-assoc-in-context-test
  (testing "constructed function can set a value from the defined context root"
    (let [mock-app-db {:root {:target-context {:context-val "test-val"
                                               :nested-context
                                               {:nested-val "nested-test-val"}}}}
          assoc-in-context (rfh/make-assoc-in-context [:root :target-context])]
      (is (= {:root {:target-context {:context-val "new-test-val"
                                      :nested-context
                                      {:nested-val "nested-test-val"}}}}
             (assoc-in-context mock-app-db [:context-val] "new-test-val"))
          "constructed function accepts a vector path")
      (is (= {:root {:target-context {:context-val "test-val"
                                      :nested-context
                                      {:nested-val "new-nested-test-val"}}}}
             (assoc-in-context mock-app-db [:nested-context :nested-val] "new-nested-test-val"))
          "constructed function can set nested values")
      (is (= {:root {:target-context {:context-val "new-test-val"
                                      :nested-context
                                      {:nested-val "nested-test-val"}}}}
             (assoc-in-context mock-app-db :context-val "new-test-val"))
          "constructed function accepts a keyword"))))
