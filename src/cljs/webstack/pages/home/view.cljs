(ns webstack.pages.home.view
  "Home page component/view definitions."
  (:require
   [webstack.re-frame-helpers :refer [<sub >evt] :as rfh]
   [webstack.pages.home.state :as state]))

(defn home-page []
  (let [content (<sub [::state/welcome-message])]
    [:div
     [:h1 "Home Page"]
     [:p content]
     [:button
      {:on-click #(>evt [::state/update-welcome-message "Welcome to Webstack!"])}
      "Yes"]
     [:button
      {:on-click #(>evt [::state/update-welcome-message "Welcome back to Webstack!"])}
      "No"]
     [:button
      {:on-click #(>evt [::state/update-welcome-message (:content state/initial-values)])}
      "Reset"]]))

(def route {:name ::home
            :view home-page
            :controllers [{:start (fn [_] (js/console.log :start "home-page start"))
                           :stop (fn [_] (js/console.log :start "home-page stop"))}]})
