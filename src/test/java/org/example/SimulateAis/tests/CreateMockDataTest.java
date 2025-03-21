package org.example.SimulateAis.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.SimulateAis.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.example.SimulateAis.models.Consent.createCustomConsent;
import static org.example.SimulateAis.models.CreateMockData.createCustomMockData;
import static org.hamcrest.Matchers.equalTo;


public class CreateMockDataTest {

    private CreateMockData createMockData;
    private Consent consent;
    private ConsentStatus consentStatus;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        createMockData =  new CreateMockData();
        consent = new Consent();
        consentStatus = new ConsentStatus(ConsentStatus.Statuses.received);
    }

    @Test
    public void testCreateMockData() {
        CreateMockData request = new CreateMockData();
        request.setRecurringIndicator(true);
        request.setFrequencyPerDay(2);
        request.setValidUntil(LocalDate.of(2019, 12, 27));

        Access access = new Access();
        access.setAllPsd2("allAccounts");
        request.setAccess(access);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/insertTestData")
                .then()
                .statusCode(200);
    }

    @Test
    public void testCreateCustomMockData() {
        createMockData = createCustomMockData(true, 2, LocalDate.of(2019, 12, 27), "allAccounts");
        given()
                .contentType(ContentType.JSON)
                .body(createMockData)
                .when()
                .post("/api/insertTestData")
                .then()
                .statusCode(200);
    }

    @Test
    public void testCreateConsent() {
        consent.setConsentId("000000013");
        consent.setRecurringIndicator(true);
        consentStatus.setConsent_status(ConsentStatus.Statuses.invalid);
        consent.setConsentStatus(consentStatus);
        consent.setValidUntil(LocalDate.of(2019, 12, 27));
        consent.setFrequencyPerDay(2);
        consent.setCombinedServiceIndicator(false);

        given()
                .contentType(ContentType.JSON)
                .body(consent)
                .when()
                .post("/api/v1/consents/000000013")
                .then()
                .statusCode(201);
    }

    @Test
    public void testCreateCustomConsent() {
        LocalDate expectedDate = LocalDate.now();
        consent = createCustomConsent("000000013", true, expectedDate, 2, "allAccounts");
        String consentId = consent.getConsentId();
        given()
                .contentType(ContentType.JSON)
                .body(consent)
                .when()
                .post("/api/v1/consents/" + consentId)
                .then()
                .statusCode(201)
                .body("consentId", equalTo(consentId))
                .body("recurringIndicator", equalTo(true))
                .body("consentStatus.consent_status", equalTo("invalid"))
                .body("validUntil", equalTo(expectedDate.toString()))
                .body("frequencyPerDay", equalTo(2))
                .body("combinedServiceIndicator", equalTo(false));
    }

}
