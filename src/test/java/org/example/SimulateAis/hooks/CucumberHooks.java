package org.example.SimulateAis.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CucumberHooks {

    private static WebDriver driver;
    private static final String DRIVER_PATH = "drivers/chromedriver.exe";
    private static final String BASE_REPORT_DIR = "target/cucumber-reports";
    private static String scenarioDirPath;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss"));
        scenarioDirPath = BASE_REPORT_DIR + "/" + timestamp + "_" + scenarioName;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                saveScreenshot(scenario);
            }
            driver.quit();
            driver = null;
        }
        moveReportFilesToScenarioDir();
    }

    private void saveScreenshot(Scenario scenario) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshotBytes, "image/png", "Failure Screenshot");

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss"));
            File destFile = new File(scenarioDirPath + "/screenshots/" + timestamp + "-on-fail.png");
            FileUtils.writeByteArrayToFile(destFile, screenshotBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveReportFilesToScenarioDir() {
        try {
            File reportJson = new File("target/cucumber-reports/cucumber-report.json");
            File reportHtml = new File("target/cucumber-reports/cucumber-html-report.html");

            if (reportJson.exists()) {
                FileUtils.copyFileToDirectory(reportJson, new File(scenarioDirPath + "/report"));
            }
            if (reportHtml.exists()) {
                FileUtils.copyFileToDirectory(reportHtml, new File(scenarioDirPath + "/report"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}