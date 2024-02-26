@auth
Feature: Create token

  Background:
    Given the user is in base uri

  Scenario: Test create token method functionality
    When the user creates a token with valid username as "admin" password as "password123"
    Then the status code should be 200
    And the response token can not be null or empty

  Scenario: Test create token method functionality with invalid cradentials
    When the user creates a token with invalid username as "123" password as "4543"
    Then the status code should be 400

  Scenario: Test create token method functionality with invalid endpoint
    When the user creates a token with username as "admin" password as "password123" and invalid endpoint as "/aut"
    Then the status code should be 404