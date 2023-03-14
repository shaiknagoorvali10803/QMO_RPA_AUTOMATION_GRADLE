package com.swacorp.rpa.pageActions;

import com.swacorp.rpa.pageObjects.LoginPageLocators;
import com.swacorp.rpa.utils.DriverFactory;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class LoginPageActions {

    private LoginPageLocators loginPageLocators ;
    private WebDriverWait wait;

    public LoginPageActions(LoginPageLocators loginPageLocators) throws IOException {
        this.loginPageLocators=loginPageLocators;
        PageFactory.initElements(DriverFactory.getDriver(),loginPageLocators);
        wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60));
    }
    public void setUserName(String strUserName) {
        loginPageLocators.userName.sendKeys(strUserName);
    }
    public void setPassword(String strPassword) {
        loginPageLocators.password.sendKeys(strPassword);
    }
    public void clickLogin() {
        loginPageLocators.login.click();
    }

    public String getLoginTitle() {
        return loginPageLocators.titleText.getText();
    }

    // Get the title of Login Page
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(loginPageLocators.errorMessage));
        return loginPageLocators.errorMessage.getText();
    }

    // LinkedIn Icon is displayed
    public Boolean getLinkedInIcon() {

        return loginPageLocators.linkedInIcon.isDisplayed();
    }

    // FaceBook Icon is displayed
    public Boolean getFaceBookIcon() {

        return loginPageLocators.faceBookIcon.isDisplayed();
    }

    // Click on Forget Your Password link
    public void clickOnForgetYourPasswordLink() {

        loginPageLocators.ForgotYourPasswordLink.click();
    }

    public void login(String strUserName, String strPassword) {
        System.out.println("entered browser value is "+System.getProperty("browserName"));
        System.out.println("entered browserName value is "+System.getProperty("Name"));
        // Fill user name
        this.setUserName(strUserName);
        // Fill password
        this.setPassword(strPassword);
        // Click Login button
        this.clickLogin();

    }
}
