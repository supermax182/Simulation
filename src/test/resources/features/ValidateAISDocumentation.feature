Feature: Validate API Documentation for AIS

  @UI
  Scenario: Validate request parameters in AIS Authorisation section
   Given User opens the AIS API documentation page
   When User waits for Request parameters specifically in Authorisation
   Then the following request parameters should exist in Authorisation:
      | Field            | Type             | Description                                                              |
      | provider_code    | string, required | Human readable Provider identifier.                                      |
      | consent_id       | string, required | The consent identifier assigned to the created resource.                 |
      | authorisation_id | string, required | Unique resource identification of the created authorization subresource. |

  @UI
  Scenario: Validate response parameters in AIS Authorisation section
    Given User opens the AIS API documentation page
    When User waits for Response parameters specifically in Authorisation
    Then the following response parameters should exist in Authorisation:
      | Field     | Type             | Description                                                                                                                        |
      | scaStatus | string, required | The link to retrieve the scaStatus of the corresponding authorization subresource. Here you can find definitions for all statuses. |