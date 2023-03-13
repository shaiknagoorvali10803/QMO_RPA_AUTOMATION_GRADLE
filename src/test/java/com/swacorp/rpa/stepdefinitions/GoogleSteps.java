package com.swacorp.rpa.stepdefinitions;

import com.google.common.util.concurrent.Uninterruptibles;
import com.swacorp.rpa.pageActions.GooglePage;
import com.swacorp.rpa.utils.DriverFactory;
import com.swacorp.rpa.utils.ScenarioContext;
import com.swacorp.rpa.utils.SeleniumUtils;
import com.swacorp.rpa.utils.Utility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

public class GoogleSteps {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private ScenarioContext scenarioContext;
    private SeleniumUtils utils;
    private Utility utility;

    private GooglePage googlePage;
    public GoogleSteps (GooglePage googlePage, ScenarioContext scenarioContext, SeleniumUtils utils,Utility utility) throws IOException {
        this.scenarioContext=scenarioContext;
        this.driver= DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(this.driver, this);
        this.googlePage=googlePage;
        this.utils=utils;
        this.utility=utility;
     }
   @Given("I am on the google site")
    public void launchSite() {
        this.googlePage.goTo();
       scenarioContext.getScenario().attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
        }
    @When("I enter {string} as a keyword")
    public void enterKeyword(String keyword) throws IOException, InterruptedException {
        this.googlePage.search(keyword);
       }

    @Then("I should see search results page")
    public void clickSearch() throws IOException, InterruptedException {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(4));
        Assert.assertTrue(this.googlePage.isAt());
        scenarioContext.getScenario().attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");
        //Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    @Then("I should see at least {int} results")
    public void verifyResults(int count) throws InterruptedException, IOException {
        Assert.assertTrue(this.googlePage.getCount() >= count);
        utils.singleClick(driver,By.xpath("//a[normalize-space()='Images']"));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[normalize-space()='Videos']")).click();
        utility.captureScreenshot();
        Thread.sleep(3000);
    }
 }
