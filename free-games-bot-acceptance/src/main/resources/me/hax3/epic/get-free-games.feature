Feature: As a lazy developer, I would like to get free games from epic automatically, therefore I can play it later.

  Scenario: Can get free games from epic if I do not own them
    Given I have an epic account via "EPIC"
    When I ran this epic free games bot
    Then the games are acquired

  Scenario: Can still check free games from epic if I do own them
    Given I have an epic account via "EPIC"
    When I ran this epic free games bot
    Then the games are acquired
