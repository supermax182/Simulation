package org.example.SimulateAis.stepdefinitions;
import io.restassured.module.jsv.JsonSchemaValidator; // ✅ Должен быть именно этот импорт!

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.example.SimulateAis.context.ScenarioContext;
import org.example.SimulateAis.context.ScenarioContextKey;

import static io.restassured.RestAssured.given;

public class DataValidationSteps {

    private final ScenarioContext scenarioContext;

    public DataValidationSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("User requests account information with Consent-Id {string}")
    public void userRequestsAccountInfo(String consentId) {
        Response response = given()
                .header("Consent-Id", consentId)
                .when()
                .get("/api/v1/card-accounts");

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

    @Then("the response should match the JSON schema {string}")
    public void responseShouldMatchJsonSchema(String schemaPath) {
        Response lastResponse = scenarioContext.get(ScenarioContextKey.LAST_RESPONSE, Response.class);

        lastResponse.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }

    @When("User requests consent status for ID {string}")
    public void userRequestsConsentStatus(String consentId) {
        Response response = given()
                .when()
                .get("/api/v1/consents/" + consentId + "/status");

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

    @When("User requests account transactions with Consent-Id {string}")
    public void userRequestsAccountTransactionsWithConsentId(String consentId) {
        Response response = given()
                .header("Consent-Id", consentId)
                .when()
                .get("/api/v1/card-accounts/transactions");

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

    @When("User requests list of accounts with Consent-Id {string}")
    public void userRequestsListOfAccountsWithConsentId(String consentId) {
        Response response = given()
                .header("Consent-Id", consentId)
                .when()
                .get("/api/v1/card-accounts");

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

}
