(ns webstack.app-db.schema-test
  (:require
   [malli.core :as m]
   [webstack.app-db.schema :as s]
   [cljs.test :refer-macros [deftest is]]))

(deftest make-app-db-schema-test
  (is (-> (#'s/make-app-db-schema) m/schema m/schema?)
      "constructed schema is valid"))
