package com.resources.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Page object for the SauceDemo login page.
 * Rule: pages hold locators and actions. Assertions live in the step definitions.
 */
public class LoginPage {

    private final SelenideElement usernameIn = $x("//input[@id='user-name']");
    private final SelenideElement passwordIn = $x("//input[@id='password']");
    private final SelenideElement loginBtn = $x("//input[@id='login-button']");
    private final SelenideElement errorMsg = $x("//h3[@data-test='error']");

    public LoginPage open() {
        Selenide.open("/");   // relative to Configuration.baseUrl
        return this;
    }

    public void login(String username, String password) {
        usernameIn.setValue(username);
        passwordIn.setValue(password);
        loginBtn.click();
    }

    public SelenideElement errorMessage() {
        return errorMsg;
    }
}
