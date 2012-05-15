(ns markify.views.marks
  (:require [markify.views.common :as common]
            [noir.response :as resp])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.page-helpers]))

(defpage "/marks" []
         (common/layout
           [:p "On your marks, ready, set, go"]))

