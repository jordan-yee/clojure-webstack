(ns webstack.config
  "Defines the system config component. Mostly, this is defined by `config.edn`.

  See https://github.com/tolitius/cprop for more details on where/how config
  values can be set."
  (:require
   [mount.core :refer [defstate]]
   [cprop.core :refer [load-config]]))

(defstate config
  "The application config, used for all mount states. This will include
  `config.edn` merged with ENV vars, and more."
  :start (load-config))
