Feature: This feature deals with Left Side Menu functionality of GoBear application

  Background:
    Given User is on "Home Page"
    When User click on Travel section
    And User click on Show My Results
    Then User is redirected to "Travel Result Page"

  @smoke
  Scenario: Verify at least 3 cards displayed
    Then There are at least 3 cards displayed

  @smoke
  Scenario: Change filter options
    When User select insurer checkbox is "Pacific Cross"
    Then Page show results with insurer "Pacific Cross" only

    And User set range of "Trip termination" from 50000 to 100000
    Then Page show results with range of "Trip termination" from 50000 to 100000 only

  @smoke
  Scenario: Change sort options
    When User sort results by price "High to Low"
    Then Page show results sorted by price "High to Low"

  @smoke @wipMobile
  Scenario: Change details of destination
    When User change details of destination into "Singapore"
    Then Page show results with destination is "Singapore" only

  @smoke @wipMobile
  Scenario: Change details of travel start-end date
    When User select travel start date with day "15" and month "Aug" and year "2019"
    When User select travel end date with day "30" and month "Aug" and year "2019"
    Then Page show results with start-end date "from 15 Aug 2019 to 30 Aug 2019"