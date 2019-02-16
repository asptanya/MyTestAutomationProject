Feature: Edit expense

  @Regression
  Scenario Outline: Edit expense
    Given I know the "Wydatki wspolne" account balance state
    And I am on the Expense list page
    When I go to expense edit view
    And I <Operation> expense amount by "75"
    Then "Wydatki wspolne" account balance is recalculated correctly after <Operation> by "75"
  Examples:
    | Operation|
    | increase |
    | decrease |
