Feature: Login

  @smoke
  Scenario: Valid user logs in successfully
    Given the user is on the login page
    When the user logs in with username "standard.user" and password "standard.password"
    Then the products page is displayed

  @regression
  Scenario Outline: Login is rejected for invalid credentials
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the error message "<error>" is displayed

    Examples:
      | username      | password           | error                                                                     |
      | locked.user   | standard.password  | locked.error  |
      | standard.user | invalid.password   | invalid.error |
      | invalid.user  | standard.password  | invalid.error |
