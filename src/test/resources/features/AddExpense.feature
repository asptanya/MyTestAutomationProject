Feature: Add new expense

  @Regression
  Scenario: Add expense for one account
    Given I am on the Main page
    When I go to Add expense page
    And I create new expense with the following data
    | Name             | Date         | Amount | Account nr1     | Contractor | Subcategory           |
    | Srodki czystosci | Current date | 120    | Wydatki wspolne | Biedronka  | Dom: Srodki czystosci |
    And I click OK button on Add expense page
    Then new expense is created