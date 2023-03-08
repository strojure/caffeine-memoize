(defproject com.github.strojure/caffeine-memoize "1.0.10-SNAPSHOT"
  :description "Clojure function memoization using Caffeine java library"
  :url "https://github.com/strojure/caffeine-memoize"
  :license {:name "The Unlicense" :url "https://unlicense.org"}

  :dependencies [[org.clojure/clojure "1.11.1" :scope "provided"]
                 [com.github.ben-manes.caffeine/caffeine "3.1.5"]]

  :profiles {:dev {:dependencies []}})
