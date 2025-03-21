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

public class PiisDocumentationSteps {

    private static final Logger LOGGER = Logger.getLogger(PiisDocumentationSteps.class.getName());
    private final WebDriver driver;

    public PiisDocumentationSteps() {
        this.driver = CucumberHooks.getDriver();
    }

    @Given("User opens the PIIS API documentation page")
    public void userOpensPiisApiDocumentationPage() {
        LOGGER.info("Opening PIIS API Documentation: https://priora.saltedge.com/docs/berlingroup/demo_bank_bg_eu/piis#confirmationoffunds-status");
        driver.get("https://priora.saltedge.com/docs/berlingroup/demo_bank_bg_eu/piis#confirmationoffunds-status");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement statusSection = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[@id='confirmationoffunds-status']")));

        LOGGER.info("Final loaded URL: " + driver.getCurrentUrl());
        Assertions.assertTrue(statusSection.isDisplayed(), "Status section not found in PIIS page!");
    }

    @Then("User waits for Request parameters specifically in PIIS Status")
    public void userWaitsForRequestParametersInPiisStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement requestParams = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[@id='confirmationoffunds-status']/following-sibling::h5[text()='Request parameters']/following-sibling::div[contains(@class, 'body-params confirmationoffunds-status-parameters')]")));

        Assertions.assertTrue(requestParams.isDisplayed(), "Request parameters not found in PIIS Status section!");
        LOGGER.info("Request parameters found in PIIS Status.");
    }

    @Then("User waits for Response parameters specifically in PIIS Status")
    public void userWaitsForResponseParametersInPiisStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement responseParams = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[@id='confirmationoffunds-status']/following-sibling::h5[text()='Response']/following-sibling::div[contains(@class, 'body-params confirmationoffunds-status-response')]")));

        Assertions.assertTrue(responseParams.isDisplayed(), "Response parameters not found in PIIS Status section!");
        LOGGER.info("Response parameters found in PIIS Status.");
    }

    @Then("the following request parameters should exist in PIIS Status:")
    public void requestParametersShouldExistInPiisStatus(DataTable table) {
        List<Map<String, String>> expectedParams = table.asMaps(String.class, String.class);

        LOGGER.info("Starting validation of request parameters in PIIS Status");

        for (Map<String, String> param : expectedParams) {
            String field = param.get("Field");
            String type = param.get("Type");
            String description = param.get("Description");

            LOGGER.info("Validating field: " + field);

            WebElement paramName = driver.findElement(By.xpath("//h5[text()='Request parameters']/following-sibling::div[contains(@class, 'body-params confirmationoffunds-status-parameters')]//div[@class='param-row param-name']//span[text()='" + field + "']"));
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

        LOGGER.info("All request parameters validated successfully in PIIS Status.");
    }

    @Then("the following response parameters should exist in PIIS Status:")
    public void responseParametersShouldExistInPiisStatus(DataTable table) {
        List<Map<String, String>> expectedParams = table.asMaps(String.class, String.class);

        LOGGER.info("Starting validation of response parameters in PIIS Status");

        for (Map<String, String> param : expectedParams) {
            String field = param.get("Field");
            String type = param.get("Type");
            String description = param.get("Description");

            LOGGER.info("Validating field: " + field);

            WebElement paramName = driver.findElement(By.xpath("//h5[text()='Response']/following-sibling::div[contains(@class, 'body-params confirmationoffunds-status-response')]//div[@class='param-row param-name']//span[text()='" + field + "']"));
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

        LOGGER.info("All response parameters validated successfully in PIIS Status.");
    }
}
