package com.swacorp.rpa.stepdefinitions;

import com.swacorp.rpa.utils.DriverFactory;
import com.swacorp.rpa.utils.LoggingException;
import com.swacorp.rpa.utils.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Hooks extends DriverFactory {
    private ScenarioContext scenarioContext;
    private WebDriver driver;
    public Hooks(ScenarioContext context){
        this.scenarioContext=context;
    }

    @Before()
    public void onlyChromeBrowser(final Scenario scenario) throws IOException {
        driver=getDriver();
        scenarioContext.setScenario(scenario);
    }
    @After()
    public void afterMethod(final Scenario scenario) throws LoggingException, InterruptedException {
        scenarioContext = new ScenarioContext();
        if (scenario.isFailed()) {
            try {
                attatchScreenshot(scenario);
                } catch (ClassCastException | IOException e) {
                throw new LoggingException(e);
            }
        }
        Thread.sleep(5000);
       DriverFactory.cleanupDriver();
    }
    private void attatchScreenshot(final Scenario scenario) throws IOException {
        scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenshot");

    }

}
