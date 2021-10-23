Feature: Validate if Users can GET transactions from the endpoints.

  Background: 
    Given User sets base-url as "http://localhost:7777/api"
    And User sets the username as "Test" and password as "Test" for Basic Authentication

  Scenario: Get All Customer Details.
    When User sets the service as "/users" and sends GET Request to the endpoint
    Then User then verifies if Status code is 200

  Scenario: Get Opening message.
    When User sets the service as "" and sends GET Request to the endpoint
    Then User then verifies if Status code is 200
    And prints the opening message in console
