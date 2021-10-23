Feature: Validate if Users can DELETE transactions from the endpoints.

  Scenario: Delete Customer.
    Given User has set the base-url as "http://localhost:7777/api"
    And User has set username as "User1" and password as "User1" for Basic Authentication
    When User sets service as "/users" and sends DELETE Request for Customer ID "6"
    Then User verifies if the Status code is 200
