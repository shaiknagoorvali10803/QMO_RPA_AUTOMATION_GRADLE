package com.swacorp.rpa.stepdefinitions;


import com.swacorp.rpa.pageActions.GooglePage;
import com.swacorp.rpa.pageActions.VisaRegistrationPage;
import com.swacorp.rpa.utils.DriverFactory;
import com.swacorp.rpa.utils.ScenarioContext;
import com.swacorp.rpa.utils.SeleniumUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;


public class VisaSteps {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private VisaRegistrationPage registrationPage;
    private SeleniumUtils utils;
    private GooglePage googlePage;
    ScenarioContext scenarioContext;
    public VisaSteps (VisaRegistrationPage visaRegistrationPage, GooglePage googlePage, ScenarioContext scenarioContext, SeleniumUtils utils) throws IOException {
        this.driver= DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(this.driver, this);
        this.registrationPage=visaRegistrationPage;
        this.googlePage=googlePage;
        this.utils=utils;
        this.scenarioContext=scenarioContext;
       }


    private void init(){

    }
    @Given("I am on VISA registration form")
    public void launchSite() {
        this.driver.navigate().to("https://vins-udemy.s3.amazonaws.com/sb/visa/udemy-visa.html");
       scenarioContext.getScenario().attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
        //Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
         }

    @When("I select my from country {string} and to country {string}")
    public void selectCountry(String from, String to) {
        this.registrationPage.setCountryFromAndTo(from, to);
    }

    @And("I enter my dob as {string}")
    public void enterDob(String dob) {
        this.registrationPage.setBirthDate(LocalDate.parse(dob));
    }

    @And("I enter my name as {string} and {string}")
    public void enterNames(String fn, String ln) {
        this.registrationPage.setNames(fn, ln);
    }

    @And("I enter my contact details as {string} and {string}")
    public void enterContactDetails(String email, String phone) {
        this.registrationPage.setContactDetails(email, phone);
    }

    @And("I enter the comment {string}")
    public void enterComment(String comment) {
        this.registrationPage.setComments(comment);
        scenarioContext.getScenario().attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
    }

    @And("I submit the form")
    public void submit() {
        //Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        this.registrationPage.submit();
        System.out.println("hashcode scenario Context "+scenarioContext.getScenario().hashCode());
        scenarioContext.getScenario().attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
        System.out.println("hashcode driver "+driver.hashCode());
        }

    @Then("I should see get the confirmation number")
    public void verifyConfirmationNumber() throws InterruptedException {
        boolean isEmpty = StringUtils.isEmpty(this.registrationPage.getConfirmationNumber().trim());
        Assert.assertFalse(isEmpty);
        Thread.sleep(2000);
    }

   }
