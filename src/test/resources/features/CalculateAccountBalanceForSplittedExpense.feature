Feature: Calculate account balance for splitted expense

  @Regression
  Scenario Outline: Calculate balance for splitted expense between two accounts
    Given I have charged bank accounts
    And I am on the Add expense page
    When I add new expense for amount "124" with the ratio <ratio1> for account "Wydatki wspolne" and ratio <ratio2> for account "Moje konto"
    Then account balance for "Wydatki wspolne" is recalculated correctly
    And "Moje konto" account balance is also recalculated correctly
    And sum of amounts taken from two accounts equal "124"
    Examples:
      | ratio1 | ratio2 |
      | "0.5"  | "0.5"  |
      | "0.8"  | "0.2"  |
      | "0.5"  | "0.9"  |
      | "0.2"  | "0.3"  |