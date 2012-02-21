(ns testproject.twitter
  (:require [fetch.remotes :as remotes]
            [crate.core :as crate]
            [jayq.core :as jq])
  (:use [testproject.utils :only [return-key-pressed get-value]])
  (:use-macros [crate.macros :only [defpartial]])
  (:require-macros [fetch.macros :as fm]))

(def selectors 
 (hash-map 
   :tweet-list (jq/$ :#tweets)
   :hash-tag (jq/$ :#tag)))

(defpartial tweet [id img text user]
  [(keyword (str "div#tweet-" id ".tweet")) [:img.tweet {:src img }] [:p.user user] [:p.text text] ])
 
(defn display-tweets [tweets]
  (jq/prepend (selectors :tweet-list)
              (crate/html [:ul
                           (map
                            (fn [{:keys [id profile-img text user]}] (tweet id profile-img text user))
                            (reverse tweets))])))

(defn get-hashtag []
  "Gets the value of the hashatag input box"
  (get-value (selectors :hash-tag)))

(defn load-tweets []
  "Function that calls the server to retrieve tweets"
  (when-let [tag (get-hashtag)]
    (fm/remote (load-tweets tag) [tweets]
               (when-not (= [] tweets)
                 (let [ftweets (flatten (map vals tweets))]
                   (display-tweets ftweets))))))

(jq/bind (selectors :hash-tag) :keypress (return-key-pressed load-tweets))