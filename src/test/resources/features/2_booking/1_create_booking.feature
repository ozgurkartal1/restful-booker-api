@booking
Feature: Create booking

  Background:
    Given the user is in base uri

  Scenario: Test create booking method functionality
    When the user create a booking with following details:
      | firstname       | Özgür      |
      | lastname        | Kartal     |
      | totalprice      | 1500       |
      | depositpaid     | true       |
      | checkin         | 2018-01-01 |
      | checkout        | 2019-01-01 |
      | additionalneeds | Breakfast  |
    Then the status code should be 200
    And the booiking id can not be empty or null
    And the response details should match with entering details

  Scenario: Test create booking functionality with invalid endpoint
    When the user create a booking with sending request to invalid enpoint as "/bookin" with following details:
      | firstname       | Özgür      |
      | lastname        | Kartal     |
      | totalprice      | 1500       |
      | depositpaid     | true       |
      | checkin         | 2018-01-01 |
      | checkout        | 2019-01-01 |
      | additionalneeds | Breakfast  |
    Then the status code should be 404

