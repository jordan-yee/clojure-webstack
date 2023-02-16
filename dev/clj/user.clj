(ns user
  #_{:clj-kondo/ignore [:refer-all]}
  (:require
   [dev :refer :all]
   [portal.api :as portal]))

(defn start-portal []
  (portal/open)
  (portal/tap))
