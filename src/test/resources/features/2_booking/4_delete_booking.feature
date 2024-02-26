@booking
Feature: Delete booking

  Background:
    Given the user is in base uri
    And the authorization process is completed

  Scenario: Test delete booking method functionality
    When the user deletes specified order
    Then the status code should be 201
    And Verify that specified order is deleted