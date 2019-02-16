Feature: Login to application

  @Regression
  Scenario Outline: Login with password
    Given I open login page
    When I login with user name <username> and password <password>
    Then I <am> logged in
    Examples:
      | username   | password   | am     |
      | "asptanya" | "12345678" | am     |
      | "abcdefgh" | "abc123de" | am not |