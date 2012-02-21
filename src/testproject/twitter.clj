(ns testproject.twitter
  (:require [cheshire.core :as json]
            [clj-http.client :as client]))

(defn twitter-search [q additional-params]
  "Calls the twitter API for a given search term. Additional parameters can be passed in"
    (let [params (merge {"q" q } additional-params)
          response (client/get "http://search.twitter.com/search.json" {:query-params params})]
    (when (= (:status response) 200)
      (-> (:body response) (json/parse-string true)))))

(defn search
  ([q] (twitter-search q {}))
  ([q additional-params] (twitter-search q additional-params)))