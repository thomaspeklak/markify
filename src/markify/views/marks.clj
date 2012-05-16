(ns markify.views.marks
  (:require [markify.views.common :as common]
            [noir.response :as resp])
  (:use [noir.core :only [defpage]]
        [hiccup.core ]
        [hiccup.form-helpers ]
        [hiccup.page-helpers]))

(defpage "/marks" []
         (common/layout
           [:h1 "Markify"]
           [:p "On your marks, ready, set, go"]
           [:div.description
            [:p 
              "Markify is an easy and fast bookmarking tool. "
              (link-to "/register" "Set up") 
              " an account and you are ready to start."]]))

(defpage "/register" {:as user}
  (common/layout
    [:h1 "Register"]
    (form-to [:post "/register"]
      [:p [:label "Username:" (text-field "username") ]]
      [:p [:label "Password:" (password-field "password") ]]
      (submit-button "Mark my words"))))
