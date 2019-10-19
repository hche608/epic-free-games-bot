Feature: As a user, I would like to login to EPIC, therefore I can get free games later.

  Scenario: Can login with EPIC
    Given I have an epic account via "EPIC"
    When I log in with the credentials
    Then I should be logged in

  Scenario: Can login with Facebook
    Given I have an epic account via "Facebook"
    When I log in with the credentials
    Then I should be logged in
