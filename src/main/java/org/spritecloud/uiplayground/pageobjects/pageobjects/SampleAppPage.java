package org.spritecloud.uiplayground.pageobjects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SampleAppPage {
    private WebDriver driver;

    // Defining the constructor
    public SampleAppPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Defining the web elements of the page
    private @FindBy(name = "UserName")
    WebElement userName;
    private @FindBy(name = "Password")
    WebElement passWord;
    private @FindBy(id = "login")
    WebElement loginBtn;
    private @FindBy(id = "loginstatus")
    WebElement loginSuccessMessage;


    //Implementing the methods
    public void setUserName(String userNameInput) {
        userName.sendKeys(userNameInput);
    }

    public void setPassWord(String passWordInput) {
        passWord.sendKeys(passWordInput);
    }

    public void login() {
        loginBtn.click();
    }

    public String getLoginSuccessMessage() {
        return loginSuccessMessage.getText();
    }
}
