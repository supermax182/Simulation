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

public class PisDocumentationSteps {

    private static final Logger LOGGER = Logger.getLogger(PisDocumentationSteps.class.getName());
    private final WebDriver driver;

    public PisDocumentationSteps() {
        this.driver = CucumberHooks.getDriver();
    }

    @Given("User opens the PIS API documentation page")
    public void userOpensPisApiDocumentationPage() {
        LOGGER.info("Opening URL: https://priora.saltedge.com/docs/berlingroup/demo_bank_bg_eu/pis#payments-status");
        driver.get("https://priora.saltedge.com/docs/berlingroup/demo_bank_bg_eu/pis#payments-status");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement statusSection = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[@id='payments-status']")));

        LOGGER.info("Final loaded URL: " + driver.getCurrentUrl());
        Assertions.assertTrue(statusSection.isDisplayed(), "Status section not found in PIS page!");
    }

    @Then("User waits for Request parameters specifically in Status")
    public void userWaitsForRequestParametersInStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement requestParams = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[@id='payments-status']/following-sibling::h5[text()='Request parameters']/following-sibling::div[contains(@class, 'body-params payments-status-parameters')]")));

        Assertions.assertTrue(requestParams.isDisplayed(), "Request parameters not found in Status section!");
        LOGGER.info("Request parameters found in Status.");
    }

    @Then("User waits for Response parameters specifically in Status")
    public void userWaitsForResponseParametersInStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement responseParams = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h2[@id='payments-status']/following-sibling::h5[text()='Response']/following-sibling::div[contains(@class, 'body-params payments-status-response')]")));

        Assertions.assertTrue(responseParams.isDisplayed(), "Response parameters not found in Status section!");
        LOGGER.info("Response parameters found in Status.");
    }

    @Then("the following request parameters should exist in Status:")
    public void requestParametersShouldExistInStatus(DataTable table) {
        List<Map<String, String>> expectedParams = table.asMaps(String.class, String.class);

        LOGGER.info("Starting validation of request parameters in Status");

        for (Map<String, String> param : expectedParams) {
            String field = param.get("Field");
            String type = param.get("Type");
            String description = param.get("Description");

            LOGGER.info("Validating field: " + field);

            WebElement paramName = driver.findElement(By.xpath("//h2[@id='payments-status']/following-sibling::div[contains(@class, 'body-params payments-status-parameters')]//div[@class='param-row param-name']//span[text()='" + field + "']"));

            WebElement paramType = paramName.findElement(By.xpath("./ancestor::div[@class='param']//div[@class='param-row param-type']//span"));
            WebElement paramDescription = paramName.findElement(By.xpath("./ancestor::div[@class='param']//div[@class='param-row param-description']//span"));

            Assertions.assertTrue(paramName.isDisplayed(), "Field " + field + " not found");
            Assertions.assertTrue(paramType.getText().replace(",", "").equalsIgnoreCase(type.replace(",", "")),
                    "Type mismatch for field " + field + ". Expected: " + type + ", Found: " + paramType.getText());
            Assertions.assertTrue(paramDescription.getText().contains(description),
                    "Description mismatch for field " + field + ". Expected: " + description + ", Found: " + paramDescription.getText());
        }

        LOGGER.info("All request parameters validated successfully in Status.");
    }

    @Then("the following response parameters should exist in Status:")
    public void responseParametersShouldExistInStatus(DataTable table) {
        List<Map<String, String>> expectedParams = table.asMaps(String.class, String.class);

        LOGGER.info("Starting validation of response parameters in Status");

        for (Map<String, String> param : expectedParams) {
            String field = param.get("Field");
            String type = param.get("Type");
            String description = param.get("Description");

            LOGGER.info("Validating field: " + field);

            WebElement paramName = driver.findElement(By.xpath("//h2[@id='payments-status']/following-sibling::div[contains(@class, 'body-params payments-status-response')]//div[@class='param-row param-name']//span[text()='" + field + "']"));

            WebElement paramType = paramName.findElement(By.xpath("./ancestor::div[@class='param']//div[@class='param-row param-type']//span"));
            WebElement paramDescription = paramName.findElement(By.xpath("./ancestor::div[@class='param']//div[@class='param-row param-description']//span"));

            Assertions.assertTrue(paramName.isDisplayed(), "Field " + field + " not found");
            Assertions.assertTrue(paramType.getText().replace(",", "").equalsIgnoreCase(type.replace(",", "")),
                    "Type mismatch for field " + field + ". Expected: " + type + ", Found: " + paramType.getText());
            Assertions.assertTrue(paramDescription.getText().contains(description),
                    "Description mismatch for field " + field + ". Expected: " + description + ", Found: " + paramDescription.getText());
        }

        LOGGER.info("All response parameters validated successfully in Status.");
    }
}
