Feature: Add account

  @Regression
  Scenario: Add new account
    Given I am on the Main page
    When I go to Add account page
    And I create new account with the following data
    | Name         | Bank name | Comment      | Account number | Account balance|
    | Test account | NBP       | Test comment | 1              | 1024           |
    And I click OK button on Add account page
    Then new account is created