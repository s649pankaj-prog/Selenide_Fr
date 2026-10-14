package com.tests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Entry point for TestNG. Run a subset with: mvn test -Dcucumber.filter.tags="@smoke"
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.tests.steps", "com.resources.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
