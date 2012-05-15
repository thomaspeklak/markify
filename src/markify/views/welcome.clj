(ns markify.views.welcome
  (:require [markify.views.common :as common]
            [noir.response :as resp])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to markify"]))

(defpage "/" []
  (resp/redirect "/marks"))

