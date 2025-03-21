package org.example.SimulateAis.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.example.SimulateAis.context.ScenarioContext;
import org.example.SimulateAis.context.ScenarioContextKey;
import org.junit.jupiter.api.Assertions;
import static org.hamcrest.Matchers.equalTo;

public class CommonSteps {

    private final ScenarioContext scenarioContext;

    public CommonSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Response lastResponse = scenarioContext.get(ScenarioContextKey.LAST_RESPONSE, Response.class);
        Assertions.assertNotNull(lastResponse, "lastResponse is null! Make sure a request was sent before checking the response.");
        int actualStatusCode = lastResponse.then().extract().statusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Unexpected status code");
    }

    @Then("the response should have error message {string}")
    public void theResponseShouldHaveErrorMessage(String expectedMessage) {
        Response lastResponse = scenarioContext.get(ScenarioContextKey.LAST_RESPONSE, Response.class);
        lastResponse.then().body("error", equalTo(expectedMessage));
    }


    @Then("the response should have header {string} with value {string}")
    public void theResponseShouldHaveHeader(String header, String expectedValue) {
        Response lastResponse = scenarioContext.get(ScenarioContextKey.LAST_RESPONSE, Response.class);
        String actualValue = lastResponse.getHeader(header);
        Assertions.assertEquals(expectedValue, actualValue, "Header " + header + " does not match expected value.");
    }

    @Then("the field {string} should be {string}")
    public void theFieldShouldBeString(String field, String expectedValue) {
        Response lastResponse = scenarioContext.get(ScenarioContextKey.LAST_RESPONSE, Response.class);
        lastResponse.then().body(field, equalTo(expectedValue));
    }

    @Then("the field {string} should be {int}")
    public void theFieldShouldBeInt(String field, int expectedValue) {
        Response lastResponse = scenarioContext.get(ScenarioContextKey.LAST_RESPONSE, Response.class);
        lastResponse.then().body(field, equalTo(expectedValue));
    }

    @Then("the field {string} should be {bool}")
    public void theFieldShouldBeBoolean(String field, boolean expectedValue) {
        Response lastResponse = scenarioContext.get(ScenarioContextKey.LAST_RESPONSE, Response.class);
        lastResponse.then().body(field, equalTo(expectedValue));
    }

    @Then("log the last API response")
    public void logTheLastApiResponse() {
        Response lastResponse = scenarioContext.get(ScenarioContextKey.LAST_RESPONSE, Response.class);

        System.out.println("==== API RESPONSE LOG ====");
        System.out.println("Status Code: " + lastResponse.getStatusCode());
        System.out.println("Headers: " + lastResponse.getHeaders());
        System.out.println("Body: " + lastResponse.getBody().asString());
        System.out.println("==========================");
    }


}
