Feature: Validate API Documentation for PIIS

  @UI
  Scenario: Validate request parameters in PIIS Status section
    Given User opens the PIIS API documentation page
    Then User waits for Request parameters specifically in PIIS Status
    And the following request parameters should exist in PIIS Status:
      | Field         | Type             | Description                                              |
      | provider_code | string, required | Human readable Provider identifier.                      |
      | consent_id    | string, required | The consent identifier assigned to the created resource. |

  @UI
  Scenario: Validate response parameters in PIIS Status section
    Given User opens the PIIS API documentation page
    Then User waits for Response parameters specifically in PIIS Status
    And the following response parameters should exist in PIIS Status:
      | Field         | Type             | Description                                                                                          |
      | consentStatus | string, required | This is the overall lifecycle status of the consent. Here you can find definitions for all statuses. |
