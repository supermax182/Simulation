@
Feature: Validate API Data Models for Consent Information


  Scenario: Validate consent data model
    When User requests consent details for ID "0000001"
    Then the response status code should be 200
    And the response should match the JSON schema "schema/consent-schema.json"


  Scenario: Validate consent status model
    When User requests consent status for ID "0000001"
    Then the response status code should be 200
    And the response should match the JSON schema "schema/consent-status-schema.json"