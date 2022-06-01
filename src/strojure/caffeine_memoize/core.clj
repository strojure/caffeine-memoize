(ns strojure.caffeine-memoize.core
  (:import (com.github.benmanes.caffeine.cache Caffeine CacheLoader))
  (:refer-clojure :exclude [memoize]))

(set! *warn-on-reflection* true)

;;••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••

(defprotocol CacheOps
  "Operations on the cache of the memoized cache."
  :extend-via-metadata true
  (clear!
    [f args]
    [f]
    "Invalidates memoized value of function `f` for `args` or all values when no
    `args` provided.")
  (put!
    [f args v]
    "Puts value `v` in the cache of function `f` for `args`."))

;;••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••

(deftype CacheKey [k args]
  Object
  (hashCode [_]
    (if (some? k) (.hashCode k), 0))
  (equals [this obj]
    (or (identical? this obj)
        (and (instance? CacheKey obj)
             (if (instance? Object k) (.equals ^Object k (.-k ^CacheKey obj))
                                      (= k (.-k ^CacheKey obj)))))))

;;••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••

(defn memoize
  "Returns function `f` memoized using Caffeine. Arguments are transformed to
  cache key using `args-key` function. Cache is configured with `cache-spec`
  string accordingly to https://tinyurl.com/caffeine-cache-spec.

  Example:

      (memoize my-function vec \"expireAfterWrite=10s\").

  "
  [f, args-key, cache-spec]
  (let [cache-key (fn [args] (CacheKey. (args-key args) args))
        cache (-> (Caffeine/from ^String cache-spec)
                  (.build (reify CacheLoader
                            (load [_ k] (apply f (.-args ^CacheKey k))))))]
    (-> (fn cache-get [& args]
          (.get cache (cache-key args)))
        (with-meta {`clear! (fn clear!
                              ([_ args] (.invalidate cache (cache-key args)))
                              ([_] (.invalidateAll cache)))
                    `put!,, (fn put!
                              [_ args v] (.put cache (cache-key args) v))}))))

;;••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••
