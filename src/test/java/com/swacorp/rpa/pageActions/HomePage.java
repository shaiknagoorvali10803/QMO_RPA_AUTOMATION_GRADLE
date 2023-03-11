package com.swacorp.rpa.pageActions;

import com.swacorp.rpa.utils.DriverFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(name = "q")
    private WebElement searchBox;
    @FindBy(name = "btnK")
    private List<WebElement> searchBtns;

    public HomePage() throws IOException {
        this.driver= DriverFactory.getDriver();
        PageFactory.initElements(this.driver, this);
        this.wait =new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void goTo(){
        this.driver.get("https://www.google.com");
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

   public boolean isAt() {
        return this.wait.until((d) -> this.searchBox.isDisplayed());
    }

}
