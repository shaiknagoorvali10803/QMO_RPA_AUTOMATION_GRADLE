package com.swacorp.rpa.pageActions;

import com.swacorp.rpa.utils.DriverFactory;
import com.swacorp.rpa.utils.ScenarioContext;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class GooglePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private ScenarioContext scenarioContext;
    @FindBy(name = "q")
    private WebElement searchBox;
    @FindBy(css = "div.g")
    private List<WebElement> results;

    @FindBy(name = "btnK")
    private List<WebElement> searchBtns;

    public GooglePage(ScenarioContext scenarioContext) throws IOException {
        super();
        this.driver= DriverFactory.getDriver();
        this.scenarioContext= scenarioContext;
        PageFactory.initElements(this.driver, this);
        this.wait =new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    public void goTo(){
        this.driver.navigate().to("https://www.google.com");
    }
    public void search(final String keyword){
        this.searchBox.sendKeys(keyword);
        this.searchBox.sendKeys(Keys.TAB);
        this.searchBtns
                .stream()
                .filter(e -> e.isDisplayed() && e.isEnabled())
                .findFirst()
                .ifPresent(WebElement::click);
    }
    public int getCount(){
        return this.results.size();
    }
    public boolean isAt() {
        return this.wait.until((d) -> this.searchBox.isDisplayed());
    }

}
