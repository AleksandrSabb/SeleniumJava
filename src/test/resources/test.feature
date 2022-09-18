Feature: Test ebay

  Background:
    Given Page is opened

  Scenario: Test login with valid creads
    When I navigate to Hello, sig in
    And I enter valid email
    And I enter valid password
    Then I see main page