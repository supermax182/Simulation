Feature: Mock Data API

  Scenario: Generate mock data
    Given User requests to generate mock data
    Then the response status code should be 200
    And the response should have header "Connection" with value "keep-alive"
    And log the last API response

