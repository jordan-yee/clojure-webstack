(ns webstack.app-db.schema
  "This namespaces defines the complete schema for re-frame's entire `app-db`,
  which is used to validate changes to the schema after every event."
  (:require
   [malli.core :as m]
   [malli.error :as me]))

;; TODO: Move this to a ns related to the home page
(def home-page-schema
  [:home
   [:map {:closed true}
    [:content :string]]])

(defn- make-app-db-schema
  "Build the schema for all of `app-db`."
  []
  [:map {:closed true}
   [:pages [:map {:closed true}
            home-page-schema]]])

(defn validate-app-db [db]
  (let [schema (make-app-db-schema)
        schema-errors (m/explain schema db)]
    (when (some? schema-errors)
      (js/console.error "app-db schema errors:\n"
                        (me/humanize schema-errors)))))
