(ns webstack.app-db.root-subs
  "Re-frame subscription registrations for the top-level keys in `app-db`, which
  represent various sub-contexts."
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::pages
 (fn [db _]
   (get db :pages)))
