(ns webstack.app-db.initial-values
  "This namespace consolidates all initial values for re-frame's app-db.")

;; TODO: Move this to a ns related to the home page
(def home-page-initial-values {:home {:content "Welcome to Webstack!"}})

(defn make-initial-values []
  (merge {:pages home-page-initial-values}))

