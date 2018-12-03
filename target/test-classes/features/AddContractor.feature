Feature: Add new contractor

  @Regression
  Scenario: Add new contractor
    Given I am on the Main page
    When I go to Add contractor page
    And I enter contractor name "Biedronka"
    And I click OK button
    Then new contractor is created