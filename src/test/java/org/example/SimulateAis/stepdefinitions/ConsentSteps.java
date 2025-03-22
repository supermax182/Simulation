package org.example.SimulateAis.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.SimulateAis.context.ScenarioContext;
import org.example.SimulateAis.context.ScenarioContextKey;
import org.example.SimulateAis.models.Consent;
import java.time.LocalDate;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ConsentSteps {

    private final ScenarioContext scenarioContext;
    private Consent consent;

    public ConsentSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("User creates a new consent with ID {string}")
    public void userCreatesANewConsent(String consentId) {
        scenarioContext.save(ScenarioContextKey.CONSENT_ID, consentId);
        this.consent = Consent.createCustomConsent(
                consentId, true, LocalDate.of(2019, 12, 27), 2, "allAccounts"
        );
    }

    @When("User sends a request to create the consent")
    public void userSendsARequestToCreateTheConsent() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(consent)
                .when()
                .post("/api/v1/consents/" + scenarioContext.get(ScenarioContextKey.CONSENT_ID, String.class));

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

    @Then("the creation response status code should be {int}")
    public void theCreationResponseStatusCodeShouldBe(int expectedStatusCode) {
        scenarioContext.get(ScenarioContextKey.LAST_RESPONSE, Response.class)
                .then().statusCode(expectedStatusCode);
    }

    @Then("the creation response should contain consentId {string}")
    public void theCreationResponseShouldContainConsentId(String expectedConsentId) {
        scenarioContext.get(ScenarioContextKey.LAST_RESPONSE, Response.class)
                .then().body("consentId", equalTo(expectedConsentId));
    }

    @Given("Consent with ID {string} exists")
    public void consentWithIDExists(String consentId) {
        userCreatesANewConsent(consentId);
        userSendsARequestToCreateTheConsent();
    }

    @When("User requests the status of consent with ID {string}")
    public void userRequestsTheStatusOfConsent(String consentId) {
        Response response = given()
                .when()
                .get("/api/v1/consents/" + consentId);

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

    @When("User changes consent status for ID {string} to {string}")
    public void userChangesConsentStatusForID(String consentId, String newStatus) {
        Response response = given()
                .when()
                .post("/debug/consent/" + consentId + "/" + newStatus);

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

    @When("User sends an empty request to create the consent with ID {string}")
    public void userSendsAnEmptyRequestToCreateTheConsentWithID(String consentId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post("/api/v1/consents/" + consentId);

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

    @When("User requests consent details for ID {string}")
    public void userRequestsConsentDetails(String consentId) {
        Response response = given()
                .when()
                .get("/api/v1/consents/" + consentId);

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

    @When("User deletes the consent with ID {string}")
    public void userDeletesTheConsent(String consentId) {
        Response response = given()
                .when()
                .delete("/api/v1/consents/" + consentId);

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }

    @Then("requesting consent with ID {string} should return 404")
    public void requestingConsentShouldReturn404(String consentId) {
        Response response = given()
                .when()
                .get("/api/v1/consents/" + consentId);

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
        response.then().statusCode(404);
    }
}
