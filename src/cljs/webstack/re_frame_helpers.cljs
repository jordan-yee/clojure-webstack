(ns webstack.re-frame-helpers
  "Wrappers, utilities, and other helpers for re-frame use."
  (:require
   [re-frame.core :as rf]
   [webstack.app-db.schema :as db-schema]
   [webstack.utils :as utils]))

(def <sub (comp deref rf/subscribe))
(def >evt rf/dispatch)

(def standard-interceptors
  "Inteceptors that should be applied to all events."
  [(utils/when-debug-v rf/debug)
   (utils/when-debug-fn rf/after db-schema/validate-app-db)])

(defn reg-event-db
  "Wrapper for `reg-event-db` that applies our application's standard
  interceptors."
  ([id handler-fn]
   (rf/reg-event-db id standard-interceptors handler-fn))
  ([id interceptors handler-fn]
   (rf/reg-event-db id
                    [standard-interceptors interceptors]
                    handler-fn)))

(def standard-interceptors-fx
  "Inteceptors that should be applied to all events."
  [(utils/when-debug-v rf/debug)
   (utils/when-debug-fn rf/after #(when % (db-schema/validate-app-db %)))])

(defn reg-event-fx
  "Wrapper for `reg-event-db` that applies our application's standard
  interceptors."
  ([id handler-fn]
   (rf/reg-event-fx id standard-interceptors-fx handler-fn))
  ([id interceptors handler-fn]
   (rf/reg-event-fx id
                    [standard-interceptors-fx interceptors]
                    handler-fn)))
