package org.spritecloud.uiplayground.pageobjects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DynamicIdPage {
    private WebDriver driver;

    // Defining the constructor

    public DynamicIdPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Defining the web elements of the page
    private @FindBy(xpath = "//button[@class=\"btn btn-primary\"][@type=\"button\"]")
    WebElement btnWithDynamicId;


    //Implementing the required methods

    public void clickDynamicBtn() {
        btnWithDynamicId.click();
    }

    public String getBtnIDAttribute() {
        return btnWithDynamicId.getAttribute("id");
    }

    public String getBtnIdText() {
        return btnWithDynamicId.getText();
    }
}
