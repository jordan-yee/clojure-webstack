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
