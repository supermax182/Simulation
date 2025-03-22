Feature: Validate API Data Models for Account Information

  Scenario: Validate account data model
    When User requests account information with Consent-Id "0000001"
    Then the response status code should be 200
    And the response should match the JSON schema "schema/card-account-schema.json"

  Scenario: Validate transactions model
    When User requests account transactions with Consent-Id "0000001"
    Then the response status code should be 200
    And the response should match the JSON schema "schema/accounts-transactions-schema.json"
