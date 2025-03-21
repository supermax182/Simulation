package org.example.SimulateAis.hooks;
import io.cucumber.java.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class CucumberHooks {
    private static WebDriver driver;
    private static final String REPORTS_FOLDER = "target/cucumber-reports/";
    private static final Logger LOGGER = Logger.getLogger(CucumberHooks.class.getName());
    private String scenarioFolder;
    private static String reportFolder;

    @Before
    public void setupUI(Scenario scenario) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            LOGGER.info("WebDriver initialized.");
        }

        // Генерируем папку с датой и названием сценария
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss"));
        scenarioFolder = REPORTS_FOLDER + timestamp + "_" + scenario.getName().replaceAll(" ", "_") + "/";
        reportFolder = scenarioFolder + "report/";

        try {
            Files.createDirectories(Paths.get(scenarioFolder, "screenshots"));
            Files.createDirectories(Paths.get(reportFolder));
        } catch (IOException e) {
            LOGGER.warning("Failed to create report folder: " + e.getMessage());
        }

        LOGGER.info("Scenario [" + scenario.getName() + "] started at: " + timestamp);
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                captureScreenshot(scenario);
            }

            copyReportFilesToScenarioFolder();

        } finally {
            if (driver != null) {
                driver.quit();
                driver = null;
                LOGGER.info("Browser closed.");
            }
        }
    }

    private void captureScreenshot(Scenario scenario) {
        try {
            String screenshotTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss"));
            String screenshotName = screenshotTimestamp + "-on-fail.png";
            Path screenshotPath = Paths.get(scenarioFolder, "screenshots", screenshotName);

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshotFile.toPath(), screenshotPath);

            LOGGER.info("Screenshot saved at: " + screenshotPath);

            byte[] screenshotBytes = Files.readAllBytes(screenshotPath);
            scenario.attach(screenshotBytes, "image/png", "Screenshot");
        } catch (Exception e) {
            LOGGER.warning("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @AfterAll
    public static void copyReportFilesToScenarioFolder() {
        Path sourceJson = Paths.get("target/cucumber-reports/cucumber-report.json");
        Path sourceHtml = Paths.get("target/cucumber-reports/cucumber-html-report.html");
        Path destinationJson = Paths.get(reportFolder, "cucumber-report.json");
        Path destinationHtml = Paths.get(reportFolder, "cucumber-html-report.html");

        try {
            Thread.sleep(1000);

            if (Files.exists(sourceJson)) {
                Files.copy(sourceJson, destinationJson, StandardCopyOption.REPLACE_EXISTING);
                LOGGER.info("Cucumber JSON report copied to: " + destinationJson);
            }
            if (Files.exists(sourceHtml)) {
                Files.copy(sourceHtml, destinationHtml, StandardCopyOption.REPLACE_EXISTING);
                LOGGER.info("Cucumber HTML report copied to: " + destinationHtml);
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.warning("Failed to copy report files: " + e.getMessage());
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized!");
        }
        return driver;
    }

}

