(ns jepsen.tendermint.core
 ;; (:refer-clojure :exclude [test])
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.pprint :refer [pprint]]
            [clojure.tools.logging :refer :all]
            [knossos.model :as model]
            [slingshot.slingshot :refer [try+]]
            [jepsen.checker :as checker]
            [jepsen.client :as client]
            [jepsen.cli :as cli]
            [jepsen.control :as c]
            [jepsen.db :as db]
            [jepsen.generator :as gen] 
            [jepsen.independent :as independent]
            [jepsen.nemesis :as nemesis]
            [jepsen.tests :as tests] 
            [jepsen.checker.timeline :as timeline]
            [jepsen.nemesis.time :as nt]
            [jepsen.control.util :as cu]
            [jepsen.util :as util :refer [timeout with-retry map-vals]]
            [jepsen.os.debian :as debian] 
            [jepsen.tendermint.db :as td]
            [jepsen.tendermint.util :refer [base-dir]]
            ))



;;tests debia/os td/db
;; (defn tendermint-test
;;   "Given an options map from the command line runner (e.g. :nodes, :ssh,
;;   :concurrency ...), constructs a test map."
;;   [opts]
  
;;   test (merge
;;         tests/noop-test
;;         opts
;;         {:name "tendermint-test"
;;          :os   debian/os
;;          :pure-generators true})
  
;;   dbt        (td/db test)
;;   test (merge test
;;          opts
;;          {:db   dbt})
;;   test)

(defn tendermint-test
  "Given an options map from the command line runner (e.g. :nodes, :ssh,
  :concurrency ...), constructs a test map."
  [opts]
  (let [test (merge opts
                    tests/noop-test
                    {:name "tendermint-test"
                     :os debian/os
                     :pure-generators true})
        dbt (td/db test)
        test-with-db (merge test {:db dbt})]
    test-with-db))
