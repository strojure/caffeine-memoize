(defproject com.github.strojure/caffeine-memoize "1.1.5-SNAPSHOT"
  :description "Clojure function memoization using Caffeine java library"
  :url "https://github.com/strojure/caffeine-memoize"
  :license {:name "Unlicense" :url "https://unlicense.org"}

  :dependencies [[org.clojure/clojure "1.11.1" :scope "provided"]
                 [com.github.ben-manes.caffeine/caffeine "3.1.1"]]

  :profiles {:dev {:dependencies []}})
