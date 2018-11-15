(ns spike.cors)


(defn- cors-preflight? [request]
  (-> request :request-method (= :options)))


(defn- cors-preflight-response [request]
  (let [headers (-> request :headers)]
    {:status  204
     :headers {"access-control-allow-origin"      (-> headers (get "origin" "*"))
               "access-control-allow-headers"     (-> headers (get "access-control-request-headers" "*"))
               "access-control-allow-methods"     (-> headers (get "access-control-request-method" "GET, POST, PUT, PATCH, DELETE"))
               "access-control-allow-credentials" "true"
               "access-control-max-age"           "2592000"}}))


(defn wrap-cors [handler]
  (fn [request]
    (if (cors-preflight? request)
      (cors-preflight-response request)
      (some-> request
              (handler)
              (update :headers assoc "access-control-allow-origin" (-> request :headers (get "origin" "*")))))))
