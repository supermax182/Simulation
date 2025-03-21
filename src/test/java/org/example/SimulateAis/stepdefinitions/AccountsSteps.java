package org.example.SimulateAis.stepdefinitions;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.SimulateAis.context.ScenarioContext;
import org.example.SimulateAis.context.ScenarioContextKey;

import static io.restassured.RestAssured.given;


public class AccountsSteps {

    private final ScenarioContext scenarioContext;

    public AccountsSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("User requests transactions with Consent-Id {string}")
    public void userRequestsTransactionsWithConsentId(String consentId) {
        Response response = given()
                .header("Consent-Id", consentId)
                .when()
                .get("/api/v1/card-accounts/transactions");

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

    @When("User requests account index with Consent-Id {string}")
    public void userRequestsAccountIndexWithConsentId(String consentId) {
        Response response = given()
                .header("Consent-Id", consentId)
                .when()
                .get("/api/v1/card-accounts");

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }
}
