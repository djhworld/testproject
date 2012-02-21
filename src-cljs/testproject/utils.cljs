(ns testproject.utils
  (:require [jayq.core :as jq]))

(def enter-key 13)

(defn- key-pressed [key-code func event]
  "If keypressed = keycode then call func"
  (when (= (.-keyCode event) key-code)
    (func)))

(defn return-key-pressed [f]
  (partial key-pressed enter-key f))

(defn get-value [selector]
  "Get the value of a selector, e.g. an input box"
  (let [value (jq/val selector)]
    (when (not= value "")
      value)))