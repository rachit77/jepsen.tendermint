;;(ns.jepsen.tendermint.db)

(ns jepsen.tendermint.db
  (:require [clojure.tools.logging :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.pprint :refer [pprint]]
            [slingshot.slingshot :refer [try+]]
            [jepsen.core :as jepsen]
            [jepsen.control :as c]
            [jepsen.db :as db]
            [jepsen.util :as util :refer [timeout with-retry map-vals]]
            [jepsen.control.util :as cu] 
            [jepsen.os.debian :as debian] 
            [jepsen.tendermint.call :as cal]
            [jepsen.tendermint.util :refer [base-dir]]))


(defn install-component!
  "Download and install a tendermint component"
  [app opts]
  (let [opt-name (keyword (str app "-url"))
        path (get opts opt-name)]
    (cu/install-archive! path (str base-dir "/" app))))

(defn init-tendermint!
   "Initializes tendermint config files"
   [test node]
    (let [pat (str base-dir "/tendermint")]
      (c/cd pat)
      (cal/croo "./tendermint" ["init" "--home" base-dir])))


(defn db
  "Etcd DB for a particular version."
  [opts]
  (reify db/DB
    (setup! [_ test node]

            ;; install the tendermint
            (c/su
             (install-component! "tendermint"  opts)
             
            ;;(jepsen/synchronize test 240)

            ;;init all nodes path: ~/root/.tendermint/config
            (init-tendermint! test node)))

            ;;start the nodes 
            ;;(start-tendermint! test node)

            ;;sleep for some time before teardown

            (teardown! [_ test node]
                       (info node "tearing down etcd"))))