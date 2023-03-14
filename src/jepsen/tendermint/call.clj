(ns jepsen.tendermint.call
  (:import (java.lang Process Runtime))
  (:require [clojure.string :as str]))

(defn croo [cmd args]
  (let [runtime (Runtime/getRuntime)
        proc (.exec runtime (into-array String (concat [cmd] args)))]
    (.waitFor proc)))
