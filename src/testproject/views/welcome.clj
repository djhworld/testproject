(ns testproject.views.welcome
  (:require [testproject.views.common :as common]
            [testproject.twitter :as tw]
            [noir.server :as server]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [noir.fetch.remotes :only [defremote]]
        [hiccup.core :only [html]]))

(defn simplify-tweet [{:keys [id_str text profile_image_url from_user]}]
  "Converts a twitter tweet response into map that is keyed by the ID of the tweet"
  (letfn [(keywordize [& x] (keyword (apply str x)))]
    (hash-map (keywordize "id-" id_str) {
                                :id id_str
                                :text text
                                :profile-img profile_image_url 
                                :user from_user
                                })))

; This is the entry point for requesting tweets. It takes a hashtag as
; an argument and calls the Twitter API to return results
(defremote load-tweets [hashtag]
  (when-let [tweets (tw/search hashtag)] ;do search request
    (let [{:keys [results]} tweets] ;get the results (tweets) from the response
      (map simplify-tweet results))))

(defpage "/welcome" []
  (common/layout
   [:div#header
    [:h2.pagetitle "Twitter Searcher"]]
    [:div#inputbar
     [:input#tag {:type "text"}]]
   [:div#tweetsbox
    [:div#tweets]]))