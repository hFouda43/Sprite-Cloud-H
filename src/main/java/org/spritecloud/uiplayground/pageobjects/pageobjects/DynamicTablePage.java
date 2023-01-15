package org.spritecloud.uiplayground.pageobjects.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DynamicTablePage {
    private WebDriver driver;

    //Defining the constructor
    public DynamicTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private int namePos = 0;
    private int cpuPOs = 0;

    //Defining the web elements of the page
    private String cpuValue = null;
    private @FindBy(css = "\"div[role='table']>div:nth-child(3)>div:nth-child(\"+(namePos+i)+\")>span\"")
    WebElement nameValue;
    private @FindBy(css = "div[role='table']>div:nth-child(2)>div>span")
    List<WebElement> headerCells;
    private @FindBy(css = "div[role='table']>div:nth-child(3)>div")
    List<WebElement> rows;
    private @FindBy(css = "p.bg-warning")
    WebElement cpuLabelValue;

    //Implementing the required methods
    public Double getCpuLoadValue(String processName) {
        for (int i = 0; i < headerCells.size(); i++) {
            if (headerCells.get(i).getText().equalsIgnoreCase("Name")) {
                namePos = i;
            } else if (headerCells.get(i).getText().equalsIgnoreCase("CPU")) {
                cpuPOs = i;
            }
        }

        for (int i = 1; i <= rows.size(); i++) {
            WebElement nameValue = driver.findElement(By.cssSelector("div[role='table']>div:nth-child(3)>div:nth-child(" + (namePos + i) + ")>span"));
            if (nameValue.getText().equalsIgnoreCase(processName)) {
                cpuValue = driver.findElement(By.cssSelector("div[role='table']>div:nth-child(3)>div:nth-child(" + (namePos + i) + ")>span:nth-child(" + (cpuPOs + 1) + ")"))
                        .getText();
                break;
            }
        }
        return Double.parseDouble(cpuValue.replaceAll("%", ""));
    }

    public double getCpuLabelValue() {
        String[] splits = cpuLabelValue.getText().split(": ");
        return Double.parseDouble(splits[1].replaceAll("%", ""));
    }


}
