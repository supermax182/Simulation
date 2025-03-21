package org.example.SimulateAis.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.SimulateAis.context.ScenarioContext;
import org.example.SimulateAis.context.ScenarioContextKey;
import org.example.SimulateAis.models.CreateMockData;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

public class MockDataSteps {

    private final ScenarioContext scenarioContext;

    public MockDataSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @When("User requests to generate mock data")
    public void userRequestsToGenerateMockData() {

        CreateMockData mockData = CreateMockData.createCustomMockData(true, 2, LocalDate.of(2019, 12, 27), "allAccounts");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(mockData)
                .when()
                .post("/api/insertTestData");

        scenarioContext.save(ScenarioContextKey.LAST_RESPONSE, response);
    }
}
