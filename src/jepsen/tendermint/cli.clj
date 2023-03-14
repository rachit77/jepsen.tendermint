(ns jepsen.tendermint.cli
  (:require [jepsen.cli :as jc]
            [clojure.pprint :refer [pprint]]
            [jepsen.tests :as tests]
            [jepsen.tendermint.core :as core]))

(def opts
  "Extra command line opts."
  [(jc/package-opt "tendermint-url" "https://github.com/tendermint/tendermint/releases/download/v0.34.22/tendermint_0.34.22_linux_amd64.tar.gz")])

                                   
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


