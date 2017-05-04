(ns gamma.emit.tag
  (:require
    [gamma.emit.emit :refer [emit]]
    [gamma.ast :refer [head body term]]))

;;;; PROGRAM

(defmethod emit :variable [db x]
  (if-let [n (:name x)]
    n
    (str "v" (:id x))))

(defmethod emit :shader [db x]
  [:span
   (interpose
     :break
     (map
      (fn [v] (emit db {:tag :declaration :variable v}))
      (filter
        #(not (re-matches #?(:cljs (js/RegExp. "gl_.*") :clj #"gl_.*") (:name %)))
        (concat (:inputs x) (:outputs x)))))
   :break

   "void main(void){"
   :break
   (interpose
     :break
     (map
      (fn [v] (emit db {:tag :declaration :variable v}))
      (filter
        #(not (if (:name %) (re-matches #?(:cljs (js/RegExp. "gl_.*") :clj #"gl_.*") (:name %))))
        (:locals x))))
   :break
   (emit db (db :root))
   :break
   "}"])

(def qualifier-order
  [[:invariant :storage :precision]
   [:storage :parameter :precision]])

(defmethod emit :declaration [db x]
  (try
    (let [v (:variable x)]
     [:span
      (if-let [s (:storage v)] (str (name s) " ") "")
      (if-let [p (:precision v)] (str (name p) " ") "")
      (name (:type v)) " " (emit db v) ";"])
    (catch #?(:cljs js/Error :clj Exception) e (println (str "declaration error on: ") (pr-str x)))))
