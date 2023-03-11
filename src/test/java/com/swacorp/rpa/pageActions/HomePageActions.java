package com.swacorp.rpa.pageActions;

import com.swacorp.rpa.pageObjects.HomePageLocators;
import com.swacorp.rpa.utils.DriverFactory;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class HomePageActions {

private HomePageLocators homePageLocators;
    WebDriverWait wait;
    public HomePageActions() throws IOException {
        this.homePageLocators = new HomePageLocators();
        PageFactory.initElements(DriverFactory.getDriver(),homePageLocators);
        wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(120));
    }

    // Get the User name from Home Page
    public String getHomePageText() {
        wait.until(ExpectedConditions.visibilityOf(homePageLocators.homePageUserName));
        return homePageLocators.homePageUserName.getText();
    }

}