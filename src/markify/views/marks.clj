(ns markify.views.marks
  (:require [markify.views.common :as common]
            [noir.response :as resp]
            [noir.validation :as validation])
  (:use [noir.core]
        [hiccup.core ]
        [hiccup.form-helpers ]
        [hiccup.page-helpers]))

;Validation and storage needs to go to the model
(defn valid? [{:keys [username password password-confirmation]}]
  (validation/rule (validation/min-length? username 1)
    [:username "Your username must have more than 1 letter."])
  (validation/rule (validation/min-length? password 8)
    [:password "Password must have at least 8 letters."])
  (validation/rule (= password password-confirmation)
    [:password-confirmation "Password confirmation does not match password"])
  (not (validation/errors? :username :password :password-confirmation)))

(defn save [credentials]
  true
)

(defpartial error-item [[first-error]]
  [:p.error first-error])

(defpartial credential-fields [{:keys [username password]}]
  [:p [:label "Username:" (text-field "username" username) ]]
  (validation/on-error :username error-item)
  [:p [:label "Password:" (password-field "password" password)]]
  (validation/on-error :password error-item))

(defpage "/marks" []
         (common/layout
           [:h1 "Markify"]
           [:p "On your marks, ready, set, go"]
           [:div.description
            [:p 
              "Markify is an easy and fast bookmarking tool. "
              (link-to "/register" "Set up") 
              " an account and you are ready to start."]]))


(defpage "/register" {:as credentials}
  (common/layout
    [:h1 "Register"]
    (form-to [:post "/register"]
      (credential-fields credentials)
      [:p [:label "Password confirmation:" (password-field "password-confirmation") ]]
      (validation/on-error :password-confirmation error-item)
      (submit-button "Mark my words"))))


(defpage [:post "/register"] {:as credentials}
  (if (valid? credentials)
    (do
      (save credentials)
      (resp/redirect "/"))
    (render "/register" credentials)
  )
)  
