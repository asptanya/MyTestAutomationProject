Feature: Login to application

  @Regression
  Scenario Outline: Login with password
    Given I open login page
    When I login with user name <username> and password <password>
    Then I am logged in
    Examples:
      | username   | password   |
      | "asptanya" | "12345678" |