Feature: Calculate account balance

  @Regression
  Scenario: Calculate balance after expense made for one account
    Given I have charged bank account
    And I am on the Add expense page
    When I add new expense for account "Wydatki wspolne" for value of "100.50"
    Then "Wydatki wspolne" account balance is recalculated correctly
