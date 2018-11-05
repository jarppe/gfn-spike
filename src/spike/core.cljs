(ns spike.core)

(defn hello [req res]
  (let [user    (-> req .-body .-name (or "world"))
        message (str "Hullo, " user)]
    (-> res
        (.status 200)
        (.send (clj->js {:message message})))))

(set! (.-exports js/module) #js {:helloworld hello})
