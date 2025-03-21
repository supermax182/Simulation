Feature: Accounts API


  Scenario: Get accounts and transactions
    Given User requests to generate mock data
    And User creates a new consent with ID "0000001"
    And User changes consent status for ID "0000001" to "valid"
    When User requests transactions with Consent-Id "0000001"
    Then the response status code should be 200
    And the response should have header "Content-Type" with value "application/json"
    And the response should have header "Transfer-Encoding" with value "chunked"
    And the response should have header "Connection" with value "keep-alive"
    And the field "balances[0].balanceAmount.currency" should be "EUR"
    And the field "balances[0].balanceAmount.amount" should be "150"
    And the field "cardAccount.accountId" should be "ACC0000001"
    And the field "cardTransactions.booked[0].cardTransactionId" should be "B2001"
    And log the last API response


  Scenario: Get account index
    Given User requests to generate mock data
    And User creates a new consent with ID "0000001"
    And User changes consent status for ID "0000001" to "valid"
    When User requests account index with Consent-Id "0000001"
    Then the response status code should be 200
    And the field "accountId" should be "ACC0000001"
    And log the last API response

  @negative
  Scenario: Get transactions with non-existent Consent
    Given User requests transactions with Consent-Id "9999999"
    Then the response status code should be 500
    And log the last API response

