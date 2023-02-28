(ns webstack.utils
  "Simple utility functions, typically for wrapping common logic used throughout
  the application.")

(defn when-debug-fn
  ;; NOTE: This cannot be a macro because it relies on `goog.DEBUG`.
  "Execute `f` only when `goog.DEBUG` is `true`, otherwise return `nil`."
  [f & args]
  (when ^boolean goog.DEBUG (apply f args)))

(defn when-debug-v
  ;; NOTE: This cannot be a macro because it relies on `goog.DEBUG`.
  "Return `v` only when `goog.DEBUG` is `true`, otherwise return `nil`."
  [v]
  (when ^boolean goog.DEBUG v))

(defn make-get-in-context
  "Return a `get-in`-like function that performs the lookup starting at the
  given `root-path` for a target context."
  [root-path]
  (fn [db ks]
    (->> ks
         (conj root-path)
         flatten
         (get-in db))))

(defn make-assoc-in-context
  "Return an `assoc-in`-like function that sets a value at a path starting at
  the given `root-path` for a target context."
  [root-path]
  (fn [db ks v]
    (->> ks
         (conj root-path)
         flatten
         (#(assoc-in db % v)))))

(defn make-update-in-context
  "Return an `update-in`-like function that sets a value at a path starting at
  the given `root-path` for a target context."
  [root-path]
  (fn [db ks f & args]
    (let [f-w-args (fn [v] (apply f v args))]
      (->> ks
           (conj root-path)
           flatten
           (#(update-in db % f-w-args))))))
