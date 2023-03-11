package com.swacorp.rpa.stepdefinitions;

import com.swacorp.rpa.pageActions.ForgotPasswordActions;
import com.swacorp.rpa.pageActions.HomePageActions;
import com.swacorp.rpa.pageActions.LoginPageActions;
import com.swacorp.rpa.utils.DriverFactory;
import com.swacorp.rpa.utils.ScenarioContext;
import com.swacorp.rpa.utils.Utility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;


public class LoginPageDefinitions {
   private LoginPageActions objLogin;
   private HomePageActions objHomePage ;
   private ForgotPasswordActions objForgotPasswordPage ;
   private ScenarioContext scenarioContext;

   private Utility utility;

   private WebDriver driver;

    public LoginPageDefinitions(LoginPageActions loginPageActions,HomePageActions homePageActions,ForgotPasswordActions forgotPasswordActions,ScenarioContext scenarioContext,Utility utility) throws IOException {
        this.objLogin=loginPageActions;
        this.objHomePage=homePageActions;
        this.objForgotPasswordPage=forgotPasswordActions;
        this.scenarioContext=scenarioContext;
        this.driver= DriverFactory.getDriver();
        this.utility=utility;
    }

    @Given("User is on HRMLogin page {string}")
    public void loginTest(String url) throws InterruptedException {
        driver.get(url);
        driver.manage().timeouts().getPageLoadTimeout();
        Thread.sleep(6000);
    }

    @When("User enters username as {string} and password as {string}")
    public void goToHomePage(String userName, String passWord) {
        objLogin.login(userName, passWord);
    }

    @When("User clicks on Forgot your Password Link")
    public void goToForgotYourPasswordPage() {
        objLogin.clickOnForgetYourPasswordLink();
    }

    @Then("User should be able to login sucessfully and new page open")
    public void verifyLogin() throws IOException, InterruptedException {
       Assert.assertTrue(objHomePage.getHomePageText().contains("Dashboard"));
        scenarioContext.getScenario().attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
     }

    @Then("User should be able to see error message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
       Assert.assertEquals(objLogin.getErrorMessage(),expectedErrorMessage);
        scenarioContext.getScenario().attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
    }
    @Then("User should be able to see LinkedIn Icon")
    public void verifyLinkedInIcon( ) {
        Assert.assertTrue(objLogin.getLinkedInIcon());
    }

    @Then("User should be able to see FaceBook Icon")
    public void verifyFaceBookIcon( ) {
        Assert.assertTrue(objLogin.getFaceBookIcon());
    }

    @Then("User should navigate to a new page")
    public void verfiyForgetYourPasswordPage() {
        Assert.assertEquals(objForgotPasswordPage.getForgotPasswordPageText(), "Reset Password");
    }

}