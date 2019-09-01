Feature: As a lazy developer, I would like to get free games from epic automatically, therefore I can play it later.

  Scenario: Can get free games from epic if I do not own them
    Given I have an epic account via "Facebook"
    And There are free games for this week
    When I ran this epic free games bot
    Then the games are acquired

  Scenario: Can not get free games from epic if I own them
    Given I have an epic account via "Facebook"
    And There are not free games for this week
    When I ran this epic free games bot
    Then do nothing