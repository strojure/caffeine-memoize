# caffeine-memoize

Clojure function memoization using [Caffeine](https://github.com/ben-manes/caffeine) java library.

[![Clojars Project](https://img.shields.io/clojars/v/com.github.strojure/caffeine-memoize.svg)](https://clojars.org/com.github.strojure/caffeine-memoize)

## Design goals

* Use Caffeine for function memoization with all benefits provided by Caffeine.

## Basic usage

```clojure
(ns user.readme-usage
  (:require [strojure.caffeine-memoize.core :as memoize]))

(defn f*
  "Original function to memoize."
  [x]
  (println 'f* [x] (System/currentTimeMillis))
  (Thread/sleep 100)
  (str "test " [x]))

(def f
  "Memoized function expiring in 60 seconds."
  (memoize/memoize #'f* first "expireAfterWrite=60s"))

(comment
  ;;; Invoke memoized function.
  (f 10)                                          ; Execution time mean : 83,530604 ns
  ;f* [10] 1654088424755
  #_"test [10]"

  ;;; Clear cache for x=10.
  (memoize/clear! f [10])
  #_nil

  ;;; Clear all cached values.
  (memoize/clear! f)
  #_nil

  ;;; Put custom value for x=10.
  (memoize/put! f [10] ::manual)
  #_nil
  (f 10)
  #_::manual
  )
```