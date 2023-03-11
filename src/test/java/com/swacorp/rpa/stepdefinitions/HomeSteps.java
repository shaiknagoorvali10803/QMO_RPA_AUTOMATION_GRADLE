package com.swacorp.rpa.stepdefinitions;

import com.swacorp.rpa.pageActions.HomePage;
import com.swacorp.rpa.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;


public class HomeSteps {
    private HomePage HomePage;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public HomeSteps (HomePage homePage) throws IOException {
        this.driver= DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(this.driver, this);
        this.HomePage=homePage;
           }

    @Given("I am Google Page")
    public void launchSite() {
        this.HomePage.goTo();
         }

    @When("Search for the Word {string}")
    public void enterKeyword(String keyword) {
        this.HomePage.search(keyword);
    }

}
