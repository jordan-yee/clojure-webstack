(ns webstack.pages.home.view
  "Home page component/view definitions."
  (:require
   [webstack.re-frame-helpers :refer [<sub >evt] :as rfh]
   [webstack.pages.home.state :as state]))

(defn home-page []
  (let [content (<sub [::state/welcome-message])
        welcome-message "Welcome to the updated Webstack!"]
    [:div
     [:h1 "Home Page"]
     [:button
      {:on-click #(>evt [::state/update-welcome-message welcome-message])}
      "Update Message"]
     [:button
      {:on-click #(>evt [::state/display-welcome-message-alert welcome-message])}
      "Welcome Alert"]
     [:p content]]))
