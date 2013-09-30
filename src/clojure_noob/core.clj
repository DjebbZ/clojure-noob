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
13.18 ; final evaluation



; ------------------------
; Defining Functions
; ------------------------

;<function-definition> ::=
  ;(defn <function-name> ["docstring"] \[<parameters>\] <function-body>)
(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH MY GOD ! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
       "BLHA BLHA BLAH AWESOME"))

(too-enthusiastic "Zelda")



; ------------------------
; Docstrings
; ------------------------

; Docstrings can be accessed in the REPL with (doc fn-name)
; Result for (doc too-enthusiastic) :
;clojure-noob.core/too-enthusiastic
;([name])
;  Return a cheer that might be a bit too enthusiastic
;nil



; ------------------------
; Parameters
; ------------------------

; Functions can have zero or more parameters
(defn no-params
  []
  "I take no params")

(defn one-param
  [x]
  (str "I take one param: " x ". It'd better be a string"))

(defn two-params
  [x y]
  (str "2 params ! => " x ", " y))

(no-params)
(one-param "March")
(two-params "Zomg" "FruityLoops")

; Function overload by arity
(defn x-chop
  "Describe the kind of chop"
  ([name chop-type]
     (str "I " chop-type " chop " name " like that"))
  ([name]
     ; reuse the previous definiton so that "karate" is the default 2nd param
     (x-chop name "karate")))

(x-chop "Kanye West")
(x-chop "Kanye West" "kung fu")

; unrelated overload behavior based on arity
(defn weird-activity
  ([]
     "Something strange is happening here")
  ([num]
     (inc num)))

(weird-activity)
(weird-activity 41)

; rest-params : args splats put in a name, with "&"
(defn disturb
  [disturbance]
  (str "Get off my lawn, " disturbance))

(defn disturbz
  [& disturbances]
  (map disturb disturbances))

(disturb "Boulay")
(disturbz "Boulay" "Tête de con" "Bitch")

; rest-params can be mixed woth normal params, but has to come last
(defn fav-things
  [name & things]
  (str "Hi, " name ", my fav things are : "
       (clojure.string/join ", " things)))

(fav-things "Mitchell" "couscous" "women" "jokes")



; ------------------------
; Destructuring
; ------------------------

; Returns the first of a collection
(defn my-first
  [[first-param]]
  first-param)

(my-first ["oven" "bike" "wizard"])
(my-first '("oven" "bike" "wizard"))
;(my-first #{"oven" "bike" "wizard"}) ; doesn't work on sets...
; (my-first {:1 "oven", :2 "bike", :3 "wizard"}) ; doesn't work on maps too...

; possible to use several named params when destructuring
(defn chooser
  [[choice-1 choice-2 & other-choices]]
  (println (str "Your first choice is " choice-1))
  (println (str "Your 2nd choice is " choice-2))
  (println (str "Your other choices are : "
                (clojure.string/join ", " other-choices))))

(chooser ["Marmelade", "Fruit Jam", "Salad", "Cocktail", "Hot sauce"])

; destructing maps
(defn treasure-location
  [{lat :lat, lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(treasure-location {:lat 28.22 :lng 45.4545})

; shorter syntax for destructuring maps, keeping track of original map passed
(defn treasure-location-2
  [{:keys [lat lng] :as treasure-map}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  (identity treasure-map))

(treasure-location-2 {:lat 10 :lng 20})



; ------------------------
; Function body
; ------------------------

; Functions returns the last form evaluated
(defn dumb-fn
  []
  (+ 1 304)
  30
  "joe")

(dumb-fn)

(defn number-comment
  [x]
  (if (> x 6)
    "Big number !" ; then case
    "Too small.")) ; else case

(number-comment 5)
(number-comment 7)



; ------------------------
; Anonymous Functions
; ------------------------

;(fn [param-list] function-body)
(map (fn [name]
       (str "Hi, " name))
     ["Luke", "Vador"])

((fn [x] (* x 3)) 8)

; It's even possible to associate an anonymous function with a name
; I suppose this is what Clojure does under the hood with defn...
(def mult-by-3 (fn [x] (* x 3)))
(mult-by-3 12)

; shorter syntax
#(* % 8)

; % is the first argument. %1, %2,.. %n when several arguments are passed.
(#(str %1 " and " %2) "Bread" "butter")

; %& for rest-params
;(#(identity %&) 1 "dude" :yippie) ; not working...



; ------------------------
; Pulling It All Together
; ------------------------

(def asym-hobbit-body-parts [{:name "head" :size 3}
                              {:name "left-eye" :size 1}
                              {:name "left-ear" :size 1}
                              {:name "mouth" :size 1}
                              {:name "nose" :size 1}
                              {:name "neck" :size 2}
                              {:name "left-shoulder" :size 3}
                              {:name "left-upper-arm" :size 3}
                              {:name "chest" :size 10}
                              {:name "back" :size 10}
                              {:name "left-forearm" :size 3}
                              {:name "abdomen" :size 6}
                              {:name "left-kidney" :size 1}
                              {:name "left-hand" :size 2}
                              {:name "left-knee" :size 2}
                              {:name "left-thigh" :size 4}
                              {:name "left-lower-leg" :size 3}
                              {:name "left-achilles" :size 1}
                              {:name "left-foot" :size 2}])

(defn has-matching-part?
  [part]
  (re-find #"^left-" (:name part)))

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps which have a :name and a :size")
