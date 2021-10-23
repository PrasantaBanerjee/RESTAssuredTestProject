Feature: Validate if Users can POST transactions to the endpoints.

  Scenario: Add New Customer.
    Given User sets the base-url as "http://localhost:7777/api"
    And User sets username as "Test" and password as "Test" for Basic Authentication
    And sets below data as the payload
      | Name    | Prasanta |
      | Age     |       26 |
      | Company | TIAA     |
    When User sets service as "/users" and sends POST Request to the endpoint
    Then User verifies if Status code is 201
