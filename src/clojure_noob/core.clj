; ------------------------
; Do Things
; ------------------------

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



; ------------------------
; Strings
; ------------------------

; Simple print statements
(println "Something")
(println "With \"quotes\" inside")

; String concatenation, no interpolation in Clojure
(def name "Chewbacca")
(println (str "Haaaaaaaaaa - " name))



; ------------------------
; Maps
; ------------------------

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



; ------------------------
; Vectors
; ------------------------

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



; ------------------------
; Lists
; ------------------------

; lists. Notice the preceding single quote.
'(1 2 3 4)

; Doesn't work for lists
(get '(1 2 3 4) 0)

(nth '(1 2 3 4) 2)



; ------------------------
; Sets
; ------------------------

; sets : collections of unique values (mixing type is possible)
#{"string" {:a 2} 2013 '(1 2 3)}

; adding an already existing element does nothing, set remains unchanged
(conj #{:a :b} :b)

(get #{1 2} 1)

(get #{1 2} 3)



; ------------------------
; Calling Functions
; ------------------------

; <function-call> ::= (<function-expression> [<arg>*])
(+ 1 2 3 4)
(* 1 2 3 4)
(first [1 2 3 4])

; a <function-expression> is anything that evaluates to a function
; + is truthy
((or + -) 1 2 3)

; return value of "and" is 1st falsy or last truthy value
((and (= 1 1) +) 1 2 3)
((and - +) 1 2 3)

((first [+ 0]) 1 2 3)

; not valid function calls

; numbers are not functions
;(1 2 3 4)

; neither are strings
;("test" 1 2 3)

; inc increments a number by 1
(inc 1)
(inc 3.3)

; map takes a function and applies it to each element of a collection
; map returns a list, even though we passed a vector
(map inc [0 2 3 4])

; dec substracts 1
(dec 3)
(map dec [1 2 3 4])



; ------------------------
; Clarifying Terminology
; ------------------------

; expressions are called forms in List
; forms are anything Clojure can evaluate
2
[1 2 3]
(inc 1)
(map inc [1 3 (inc 4)])

; function call : form enclosed with parentheses where 1st elem is a function
; "if" is not a function... A macro ? Don't know yet
(if true 1 2)

; a function call kicks off the evaluation process,
; which evaluates each subform recursively
(+ (inc 12) (/ (- 20 2) 100))

; the above form evaluates like this :
(+ 13 (/ (- 20 2) 100))
(+ 13 (/ 18 100))
(+ 13 0.18)
; 13.18 ; final evaluation
