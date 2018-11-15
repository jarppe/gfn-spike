(ns spike.ring
  (:require [clojure.string :as str]))


;;
;; Request:
;;


(defn- node-request->ring-request [node-request]
  {:request-method (-> node-request .-method (str/lower-case) (keyword))
   :uri            (-> node-request .-path)
   :query-string   (-> node-request .-query)
   :headers        (-> node-request .-headers (js->clj))
   :body           (-> node-request .-body (js->clj {:keywordize-keys true}))})


;;
;; Response:
;;


(defn- set-headers [node-response headers]
  (doseq [[k v] headers]
    (.set node-response k v)))


(defn- write-to-node-response [ring-response node-response]
  (doto node-response
    (.status (-> ring-response :status (or 200)))
    (set-headers (-> ring-response :headers))
    (.send (-> ring-response :body (or "") (clj->js)))))


;;
;; Handler:
;;


(def not-found {:status 404
                :body   "The will is strong, but I'm unable to serve your request"})


(defn make-handler [handler]
  (fn [node-request node-response]
    (try
      (-> (node-request->ring-request node-request)
          (handler)
          (or not-found)
          (write-to-node-response node-response))
      (catch js/Error e
        (doto node-response
          (.status 500)
          (.send (str "unexpected error: " e)))))))


;;
;; Export:
;;


(defn export-as [handler handler-name]
  (set! (.-exports js/module) (clj->js {handler-name handler})))
