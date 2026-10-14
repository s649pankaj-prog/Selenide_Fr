package com.resources.hooks;

import com.resources.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.setUp();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && DriverManager.isStarted()) {
            scenario.attach(DriverManager.screenshot(), "image/png", scenario.getName());
        }
        DriverManager.quit();
    }
}