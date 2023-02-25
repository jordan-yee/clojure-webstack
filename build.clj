(ns build
  "This build script uses `tools.build` for use with the Clojure CLI:
  https://clojure.org/guides/tools_build

  Here's the `clojure.tools.build` API reference:
  https://clojure.github.io/tools.build/clojure.tools.build.api.html"
  (:require [clojure.tools.build.api :as b]))

(def lib 'webstack) ; could be used for `b/write-pom`
(def version (format "0.0.%s" (b/git-count-revs nil))) ; TODO: process for updating?
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def uber-file (format "target/%s-%s-standalone.jar" (name lib) version))

(defn clean [_]
  (b/delete {:path "target"}))

(defn clean-client [_]
  (b/delete {:path "resources/public/js/app"}))

(defn uber [_]
  (clean nil)
  (b/copy-dir {:src-dirs ["src/clj" "resources"]
               :target-dir class-dir})
  (b/compile-clj {:basis basis
                  :src-dirs ["src/clj"]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis basis
           :main 'webstack.core}))
