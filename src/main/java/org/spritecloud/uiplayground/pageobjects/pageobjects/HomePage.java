package org.spritecloud.uiplayground.pageobjects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    //Defining the constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Defining the web elements of the page
    private @FindBy(linkText = "Dynamic Table")
    WebElement dynamicTableLink;
    private @FindBy(linkText = "Overlapped Element")
    WebElement overlappedElementLink;

    private @FindBy(linkText = "Sample App")
    WebElement sampleAppLink;
    private @FindBy(linkText = "Dynamic ID")
    WebElement dynamicIdLink;

    //Implementing the required methods
    public void goTo() {
        driver.get("http://www.uitestingplayground.com/");

    }

    public void goToDynamicTablePage() {
        //driver.get("http://www.uitestingplayground.com/dynamictable");
        dynamicTableLink.click();
    }

    public void goToOverlappedElementPage() {
        overlappedElementLink.click();
    }

    public void goToSampleAppPage() {
        sampleAppLink.click();
    }

    public void goToDynamicIdPage() {
        dynamicIdLink.click();
    }
}
