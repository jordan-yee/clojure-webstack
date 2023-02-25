(ns webstack.utils-test
  (:require
   [webstack.utils :as utils]
   [cljs.test :refer-macros [deftest is]]))

(deftest when-debug-fn-test
  (is (= 2 (utils/when-debug-fn inc 1))))
