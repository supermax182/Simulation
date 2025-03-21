package org.example.SimulateAis.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.example.SimulateAis.hooks.CucumberHooks;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AisDocumentationSteps {

    private static final Logger LOGGER = Logger.getLogger(AisDocumentationSteps.class.getName());
    private final WebDriver driver;

    public AisDocumentationSteps() {
        this.driver = CucumberHooks.getDriver();
    }

    @Given("User opens the AIS API documentation page")
    public void userOpensAisApiDocumentationPage() {
        LOGGER.info("Opening AIS API Documentation: https://priora.saltedge.com/docs/berlingroup/demo_bank_bg_eu/ais#consents-authorisation");
        driver.get("https://priora.saltedge.com/docs/berlingroup/demo_bank_bg_eu/ais#consents-authorisation");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement authorisationSection = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[@id='consents-authorisation']")));

        LOGGER.info("Final loaded URL: " + driver.getCurrentUrl());
        Assertions.assertTrue(authorisationSection.isDisplayed(), "Authorisation section not found in AIS page!");
    }

    @Then("User waits for Request parameters specifically in Authorisation")
    public void userWaitsForRequestParametersInAuthorisation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement requestParams = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[@id='consents-authorisation']/following-sibling::h5[text()='Request parameters']/following-sibling::div[contains(@class, 'body-params consents-authorisation-parameters')]")));

        Assertions.assertTrue(requestParams.isDisplayed(), "Request parameters not found in Authorisation section!");
        LOGGER.info("Request parameters found in Authorisation.");
    }

    @Then("User waits for Response parameters specifically in Authorisation")
    public void userWaitsForResponseParametersInAuthorisation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement responseParams = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[@id='consents-authorisation']/following-sibling::h5[text()='Response']/following-sibling::div[contains(@class, 'body-params consents-authorisation-response')]")));

        Assertions.assertTrue(responseParams.isDisplayed(), "Response parameters not found in Authorisation section!");
        LOGGER.info("Response parameters found in Authorisation.");
    }

    @Then("the following request parameters should exist in Authorisation:")
    public void requestParametersShouldExistInAuthorisation(DataTable table) {
        List<Map<String, String>> expectedParams = table.asMaps(String.class, String.class);

        LOGGER.info("Starting validation of request parameters in Authorisation");

        for (Map<String, String> param : expectedParams) {
            String field = param.get("Field");
            String type = param.get("Type");
            String description = param.get("Description");

            LOGGER.info("Validating field: " + field);

            WebElement paramName = driver.findElement(By.xpath("//h5[text()='Request parameters']/following-sibling::div[contains(@class, 'body-params consents-authorisation-parameters')]//div[@class='param-row param-name']//span[text()='" + field + "']"));
            LOGGER.info("Field found: " + paramName.getText());

            WebElement paramType = paramName.findElement(By.xpath("./ancestor::div[@class='param']//div[@class='param-row param-type']//span"));
            LOGGER.info("Expected type: " + type + " | Found: " + paramType.getText());

            WebElement paramDescription = paramName.findElement(By.xpath("./ancestor::div[@class='param']//div[@class='param-row param-description']//span"));
            LOGGER.info("Expected description: " + description + " | Found: " + paramDescription.getText());

            Assertions.assertTrue(paramName.isDisplayed(), "Field " + field + " not found");
            Assertions.assertTrue(paramType.getText().replace(",", "").equalsIgnoreCase(type.replace(",", "")),
                    "Type mismatch for field " + field + ". Expected: " + type + ", Found: " + paramType.getText());
            Assertions.assertTrue(paramDescription.getText().contains(description),
                    "Description mismatch for field " + field + ". Expected: " + description + ", Found: " + paramDescription.getText());
        }

        LOGGER.info("All request parameters validated successfully in Authorisation.");
    }

    @Then("the following response parameters should exist in Authorisation:")
    public void responseParametersShouldExistInAuthorisation(DataTable table) {
        List<Map<String, String>> expectedParams = table.asMaps(String.class, String.class);

        LOGGER.info("Starting validation of response parameters in Authorisation");

        for (Map<String, String> param : expectedParams) {
            String field = param.get("Field");
            String type = param.get("Type");
            String description = param.get("Description");

            LOGGER.info("Validating field: " + field);

            WebElement paramName = driver.findElement(By.xpath("//h5[text()='Response']/following-sibling::div[contains(@class, 'body-params consents-authorisation-response')]//div[@class='param-row param-name']//span[text()='" + field + "']"));
            LOGGER.info("Field found: " + paramName.getText());

            WebElement paramType = paramName.findElement(By.xpath("./ancestor::div[@class='param']//div[@class='param-row param-type']//span"));
            LOGGER.info("Expected type: " + type + " | Found: " + paramType.getText());

            WebElement paramDescription = paramName.findElement(By.xpath("./ancestor::div[@class='param']//div[@class='param-row param-description']//span"));
            LOGGER.info("Expected description: " + description + " | Found: " + paramDescription.getText());

            Assertions.assertTrue(paramName.isDisplayed(), "Field " + field + " not found");
            Assertions.assertTrue(paramType.getText().replace(",", "").equalsIgnoreCase(type.replace(",", "")),
                    "Type mismatch for field " + field + ". Expected: " + type + ", Found: " + paramType.getText());
            Assertions.assertTrue(paramDescription.getText().contains(description),
                    "Description mismatch for field " + field + ". Expected: " + description + ", Found: " + paramDescription.getText());
        }

        LOGGER.info("All response parameters validated successfully in Authorisation.");
    }
}
