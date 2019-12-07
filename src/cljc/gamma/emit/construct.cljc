(ns gamma.emit.construct
  (:require
    [gamma.emit.emit :refer [emit]]
    [gamma.ast :refer [head body term ]]))

(defmethod emit :constructor [db x]
  [:group
   (name (head x))
   "("
   [:line ""]
   [:nest 2
    (interpose [:span "," :line]
               (map #(emit db (db %)) (body x)))]
   ")"])
