(ns webstack.router.schema
  "Schema for router-related state in app-db.")

(def schema [:map [:matched-route :map]])
