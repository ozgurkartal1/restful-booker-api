@booking
Feature: Get a booking

  Background:
    Given the user is in base uri

  Scenario: Test get a booking method functionality
    When the user get a booking with specified id
    Then the status code should be 200
    And the response details should match specified booking details

  Scenario: Test get a booking method functionality with invalid order id
    When the user get a booking with invalid id as 56435
    Then the status code should be 404

  Scenario: Test get a booking method functionality with invalid endpoint
    When the user get a booking with specified id and invalid enpoint as "/bookin"
    Then the status code should be 404
