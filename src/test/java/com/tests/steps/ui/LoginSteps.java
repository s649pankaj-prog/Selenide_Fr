package com.tests.steps.ui;

import com.resources.config.ConfigReader;
import com.resources.pages.LoginPage;
import com.resources.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

/** Step definitions only call page methods and make assertions. No locators here. */
public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final ProductsPage productsPage = new ProductsPage();

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage.open();
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsIn(String username, String password) {
        loginPage.login(ConfigReader.get(username), ConfigReader.get(password));
    }

    @Then("the products page is displayed")
    public void theProductsPageIsDisplayed() {

        productsPage.title().shouldHave(exactText("Products"));
        productsPage.products().first().shouldBe(visible);
    }

    @Then("the error message {string} is displayed")
    public void theErrorMessageIsDisplayed(String expected) {
        loginPage.errorMessage().shouldHave(exactText(ConfigReader.get(expected)));
    }
}
