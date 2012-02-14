(ns testproject.views.welcome
  (:require [testproject.views.common :as common]
            [noir.server :as server]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to tproject"]))

