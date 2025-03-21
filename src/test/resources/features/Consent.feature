
Feature: API Testing for Consent Management

@Run
  Scenario: Create a new consent
    Given User creates a new consent with ID "0000001"
    When User sends a request to create the consent
    Then the creation response status code should be 201
    And the creation response should contain consentId "0000001"
    And the field "recurringIndicator" should be true
    And the field "consentStatus.consent_status" should be "invalid"
    And the field "validUntil" should be "2019-12-27"
    And the field "frequencyPerDay" should be 2
    And the field "combinedServiceIndicator" should be false
    And log the last API response

  Scenario: Get consent status
    Given Consent with ID "0000001" exists
    When User requests the status of consent with ID "0000001"
    Then the response status code should be 200
    And the field "consent_status" should be "invalid"
    And log the last API response

  Scenario: Get consent details - show consent
    Given Consent with ID "0000001" exists
    When User requests consent details for ID "0000001"
    Then the response status code should be 200
    And the field "consentId" should be "0000001"
    And the field "recurringIndicator" should be true
    And the field "frequencyPerDay" should be 2
    And the field "validUntil" should be "2019-12-27"
    And the field "consentStatus.consent_status" should be "invalid"
    And the field "combinedServiceIndicator" should be false
    And log the last API response

  Scenario: Change consent status to valid
    Given User creates a new consent with ID "0000001"
    When User changes consent status for ID "0000001" to "valid"
    Then the response status code should be 200
    And log the last API response

#  @Run
#  Scenario: Change consent status to invalid
#    Given User creates a new consent with ID "0000001"
#    When User changes consent status for ID "0000001" to "invalid"
#    Then the response status code should be 200

  @negative
  Scenario: Get non-existent consent
    Given User requests consent details for ID "9999999"
    Then the response status code should be 404
    And log the last API response

  @negative
  Scenario: Delete non-existent consent
    Given User deletes the consent with ID "9999999"
    Then the response status code should be 404
    And log the last API response

  @smoke
  Scenario: Delete consent
    Given Consent with ID "0000001" exists
    When User deletes the consent with ID "0000001"
    Then the response status code should be 204
    And requesting consent with ID "0000001" should return 404
    And log the last API response


  @negative
  Scenario: Get transactions with revoked Consent
    Given User requests to generate mock data
    And User creates a new consent with ID "0000003"
    And User changes consent status for ID "0000003" to "revoked"
    When User requests transactions with Consent-Id "0000003"
    Then the response status code should be 500
    And log the last API response

  Scenario: Create consent with empty body
    Given User sends an empty request to create the consent with ID "0000003"
    Then the response status code should be 201
    And log the last API response
