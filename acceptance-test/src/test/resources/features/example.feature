@TravelBuddy
Feature: User would like to get travelBuddies

  Background:
    Given the following travelBuddies exists in the library
      | code | description                 |
      | 1    | Twinkle twinkle little star |
      | 2    | Johnny Johnny Yes Pappa     |

  Scenario: User should be able to get all travelBuddies
    When user requests for all travelBuddies
    Then the user gets the following travelBuddies
      | code | description                 |
      | 1    | Twinkle twinkle little star |
      | 2    | Johnny Johnny Yes Pappa     |

  Scenario: User should be able to get travelBuddies by code
    When user requests for travelBuddies by code "1"
    Then the user gets the following travelBuddies
      | code | description                 |
      | 1    | Twinkle twinkle little star |

  Scenario: User should get an appropriate NOT FOUND message while trying get travelBuddies by an invalid code
    When user requests for travelBuddies by id "10000" that does not exists
    Then the user gets an exception "TravelBuddy with code: [10000] does not exists"
