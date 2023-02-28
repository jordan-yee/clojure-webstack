(ns webstack.router.routes
  (:require
   [webstack.pages.home.route :as home]))

(def other-route
  "XXX: Remove me I'm only here to enable a routing example."
  {:name ::other
   :view (fn [] [:div "other"])
   :controllers [{:start (fn [_] (js/console.log :start "other-page start"))
                  :stop (fn [_] (js/console.log :start "other-page stop"))}]})

(def routes
  ["/"
   ["" home/route]
   ["other" other-route]])
