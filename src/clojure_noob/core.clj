(ns clojure-noob.core
  (:gen-class))

; main entry point of program
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Yo!"))

; a vector is like an array in JS
(def failed-protagonist-names
  ["Larry Potter"
   "Doreen the Explorer"
   "The Incredible Bulk"])

; Accessing a vector means using the vector as a function
(failed-protagonist-names 2)

; Simple print statements
(println "Something")
(println "With \"quotes\" inside")
