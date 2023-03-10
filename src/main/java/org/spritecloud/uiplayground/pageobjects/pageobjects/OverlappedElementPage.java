package org.spritecloud.uiplayground.pageobjects.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OverlappedElementPage {
    private WebDriver driver;

    //Defining the constructor
    public OverlappedElementPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Defining the web elements of the page
    private @FindBy(xpath = "//div[@class=\"container\"][1]/div/div[1]")
    WebElement scrollableDiv;

    private @FindBy(id = "name")
    WebElement nameField;

    //Implementing the required methods
    public void setNameFieldValue(String inputText) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", nameField);
        nameField.sendKeys(inputText);

    }

    public String getNameFieldValue() {
        return nameField.getAttribute("value");

    }
}
