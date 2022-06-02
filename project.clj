(defproject com.github.strojure/caffeine-memoize "1.0.4"
  :description "Clojure function memoization using Caffeine java library"
  :url "https://github.com/strojure/caffeine-memoize"
  :license {:name "Apache License 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0.html"}

  :dependencies [[org.clojure/clojure "1.11.1" :scope "provided"]
                 [com.github.ben-manes.caffeine/caffeine "3.1.1"]]

  :profiles {:dev {:dependencies []}})
