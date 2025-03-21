
Feature: Validate PIS API Documentation for Status Section


  Scenario: Validate request parameters in PIS Status section
    Given User opens the PIS API documentation page
    Then User waits for Request parameters specifically in Status
    And the following request parameters should exist in Status:
      | Field           | Type             | Description                                           |
      | provider_code   | string, required | Human readable Provider identifier.                   |
      | payment_product | string, required | The addressed payment product.                        |
      | payment_id      | string, required | Payment identifier on Salt Edge PSD2 Compliance side. |


#  @Run
#  Scenario: Validate request parameters in PIS Status section
#    Given User opens the PIS API documentation page
#    Then the Request parameters section should be present
#    And the following request parameters should exist:
#      | Field           | Type             | Description                                 | Allowed Values                                                               | Can Raise                      |
#      | provider_code   | string, required | Human readable Provider identifier.         | -                                                                            | -                              |
#      | payment_product | string, required | The addressed payment product.              | sepa-credit-transfers, instant-sepa-credit-transfers, faster-payment-service | ProductUnknown, ProductInvalid |
#      | payment_id      | string, required | Payment identifier on Salt Edge compliance. | -                                                                            | ResourceUnknown                |

  @
  Scenario: Validate response parameters in PIS Status section
    Given User opens the PIS API documentation page
    Then User waits for Response parameters specifically in Status
    And the following response parameters should exist in Status:
      | Field             | Type              | Description                                                                                                              |
      | transactionStatus | string, required  | The transaction status is filled with codes of the ISO 20022 data table. Here you can find definitions for all statuses. |
      | fundsAvailable    | boolean, optional | Funds availability information from ASPSP.                                                                               |