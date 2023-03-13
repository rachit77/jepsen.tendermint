(ns jepsen.tendermint.cli
  (:require [jepsen.cli :as jc]
            [clojure.pprint :refer [pprint]]
            [jepsen.tests :as tests]
            [jepsen.tendermint.core :as core]))

(def opts
  "Extra command line opts."
  [(jc/package-opt "tendermint-url" "https://github.com/tendermint/tendermint/archive/refs/tags/v0.34.23.tar.gz")])


(defn -main
  "Handles command line arguments. And run a test."
  [& args]
  (jc/run! (jc/single-test-cmd {:test-fn core/tendermint-test
                                  :opt-spec opts})
            args))


;; (defn -main
;;   "Handles command line arguments. And run a test."
;;   [& args]
;;   (jc/run! (jc/single-test-cmd {:test-fn tests/noop-test
;;                                 :opt-spec opts})

;;            args))


