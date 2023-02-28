(ns webstack.pages.home.route
  (:require
   [webstack.pages.home.view :refer [home-page]]))

(def route {:name ::home
            :view home-page
            :controllers [{:start (fn [_] (js/console.log :start "home-page start"))
                           :stop (fn [_] (js/console.log :start "home-page stop"))}]})
