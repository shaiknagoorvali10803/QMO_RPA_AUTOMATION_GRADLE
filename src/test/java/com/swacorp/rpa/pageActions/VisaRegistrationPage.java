package com.swacorp.rpa.pageActions;

import com.swacorp.rpa.utils.DriverFactory;
import com.swacorp.rpa.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

public class VisaRegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(VisaRegistrationPage.class);

    @FindBy(id ="first_4")
    private WebElement firstName;

    @FindBy(id ="last_4")
    private WebElement lastName;

    @FindBy(id = "input_46")
    private WebElement fromCountry;

    @FindBy(id = "input_47")
    private WebElement toCountry;

    @FindBy(id = "input_24_month")
    private WebElement month;

    @FindBy(id = "input_24_day")
    private WebElement day;

    @FindBy(id = "input_24_year")
    private WebElement year;

    @FindBy(id = "input_6")
    private WebElement email;

    @FindBy(id = "input_27_phone")
    private WebElement phone;

    @FindBy(id = "input_45")
    private WebElement comments;

    @FindBy(id = "submitBtn")
    private WebElement submit;

    @FindBy(id = "requestnumber")
    private WebElement requestNumber;

   private SeleniumUtils utils;

    public VisaRegistrationPage(SeleniumUtils utils) throws IOException {
        this.driver= DriverFactory.getDriver();
        PageFactory.initElements(this.driver, this);
        this.utils=utils;
        this.wait =new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void setNames(String firstName, String lastName){
        logger.info("Getting names : " + firstName);
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
    }

    public void setCountryFromAndTo(String countryFrom, String countryTo){
        new Select(this.fromCountry).selectByValue(countryFrom);
        new Select(this.toCountry).selectByValue(countryTo);
    }

    public void setBirthDate(LocalDate localDate){
        new Select(this.year).selectByVisibleText(String.valueOf(localDate.getYear()));
        new Select(this.day).selectByVisibleText(String.valueOf(localDate.getDayOfMonth()));
        new Select(this.month).selectByValue(localDate.getMonth().toString());
    }
    public void setContactDetails(String email, String phone){
        this.email.sendKeys(email);
        this.phone.sendKeys(phone);
    }

    public void setComments(String comments){
        this.comments.sendKeys(Objects.toString(comments, ""));
    }

    public void submit(){
        utils.clickUsingJavaScript(driver,submit);
    }

    public String getConfirmationNumber(){
        this.wait.until((d) -> this.requestNumber.isDisplayed());
        return this.requestNumber.getText();
    }

}
