package org.example.SimulateAis.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.example.SimulateAis",
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber-report.json",
                "html:target/cucumber-reports/cucumber-html-report.html",
        }, publish = true,
        tags = "@Run"
)
public class CucumberTestRunner {

}
