@booking
Feature: Update booking

  Background:
    Given the user is in base uri
    And the authorization process is completed

  Scenario: Test update booking method functionality
    When the user update a specific booking with following details:
      | firstname       | Mehmet     |
      | lastname        | Toprak     |
      | totalprice      | 1200       |
      | depositpaid     | true       |
      | checkin         | 2019-01-01 |
      | checkout        | 2020-01-01 |
      | additionalneeds | Breakfast  |
    Then the status code should be 200
    And the run time must be lower than 1500 ms
    And the response details should match with specified details

  Scenario: Test update booking method functionality with invalid booking id
    When the user update a booking with invalid booking id as "321432434" following details:
      | firstname       | Mehmet     |
      | lastname        | Toprak     |
      | totalprice      | 1200       |
      | depositpaid     | true       |
      | checkin         | 2019-01-01 |
      | checkout        | 2020-01-01 |
      | additionalneeds | Breakfast  |
    Then the status code should be 404
