package com.resources.utils;

import com.resources.config.ConfigReader;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/** One place for browser configuration, start-up checks, screenshots and shutdown. */
public final class DriverManager {

    private DriverManager() {
    }

    public static void setUp() {
        Configuration.baseUrl = ConfigReader.get("base.url");
        Configuration.browser = ConfigReader.get("browser");
        Configuration.headless = ConfigReader.getBoolean("headless");
        Configuration.browserSize = ConfigReader.get("browser.size");
        Configuration.timeout = ConfigReader.getLong("timeout.ms");
        Configuration.reportsFolder = "target/selenide-reports";

        // Optional: set remote.url in config or -Dremote.url=... to run on a Selenium Grid (later)
        String remoteUrl = ConfigReader.getOrDefault("remote.url", "");
        if (!remoteUrl.isBlank()) {
            Configuration.remote = remoteUrl;
        }

        if ("chrome".equalsIgnoreCase(Configuration.browser)) {
            Configuration.browserCapabilities = chromeOptions();
        }
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        // Stops Chrome's "change your password" popup, which can block SauceDemo tests
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        return options;
    }

    public static boolean isStarted() {
        return WebDriverRunner.hasWebDriverStarted();
    }

    public static byte[] screenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void quit() {
        Selenide.closeWebDriver();
    }
}
