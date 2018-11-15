(ns spike.core
  (:require [spike.ring :as ring]
            [spike.cors :as cors]))


(defn ping [request]
  (when (and (-> request :request-method (= :post))
             (-> request :uri (= "/ping")))
    {:body {:message "pong"}}))


(defn hello [request]
  (when (and (-> request :request-method (= :post))
             (-> request :uri (= "/")))
    (let [user    (-> request :body :name (or "world!!"))
          message (str "Hullo, " user)]
      {:body {:message message}})))


(def handler
  (-> (some-fn ping
               hello)
      (cors/wrap-cors)
      (ring/make-handler)
      (ring/export-as :helloworld)))
