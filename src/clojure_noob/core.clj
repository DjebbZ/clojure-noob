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

; String concatenation, no interpolation in Clojure
(def name "Chewbacca")
(println (str "Haaaaaaaaaa - " name))

; maps : key-value pairs. Here :a is a keyword
{:a 1 ; number
 :b "boring example" ; string
 :c []} ; vector

; associating "string key" with the "plus" function
{"string key" +}

; look up in maps
(get {:a 0} :a)

(get {:a 0, :b 1} :b)

(get {:a 0, :b {:c "nested maps"}} :b)

; use a map as a function
({:a 0, :b {:c "nested maps"}} :b)

; use a keyword as a function
(:a {:a 1 :b 2})

; vectors
[3 2 1]

; get by index
(get [3 2 1] 0)

; mixing types in vector
(get ["a" {:a 1} 3] 1)

; using vector as function
(["a" {:a 1} 3] 1)

; nesting values as functions
((["a" {:a 1} 3] 1) :a)

; lists. Notice the preceding single quote.
'(1 2 3 4)

; Doesn't work for lists
(get '(1 2 3 4) 0)

(nth '(1 2 3 4) 2)
