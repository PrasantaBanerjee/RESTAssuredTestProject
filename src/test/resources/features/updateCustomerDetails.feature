Feature: Validate if Users can UPDATE transactions using the endpoints.

  Scenario: Update Customer.
    Given User has set base-url as "http://localhost:7777/api"
    And User has set the username as "Test" and password as "Test" for Basic Authentication
    And sends below data as the payload
      | firstName | Prasanta |
      | lastName  | Banerjee |
      | age       |       25 |
    When User sets the service as "/users" and sends PUT Request for Customer ID "88776"
    Then User will verify if the Status code is 200
