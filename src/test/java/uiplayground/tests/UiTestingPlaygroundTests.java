package uiplayground.tests;

import org.openqa.selenium.WebDriver;
import org.spritecloud.uiplayground.pageobjects.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import uiplayground.testcomponents.BaseTest;

import java.io.IOException;

public class UiTestingPlaygroundTests extends BaseTest {
    WebDriver driver;

    //Initializing the driver
    {
        try {
            driver = initializeDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Defining the required objects for the tests
    HomePage homePage = new HomePage(driver);
    DynamicTablePage dynamicTablePage = new DynamicTablePage(driver);
    OverlappedElementPage overlappedElementPage = new OverlappedElementPage(driver);
    SampleAppPage sampleAppPage = new SampleAppPage(driver);
    DynamicIdPage dynamicIdPage = new DynamicIdPage(driver);


    // Testing the dynamic table page
    @Test
    public void dynamicTablePageTest() {
        homePage.goTo();
        homePage.goToDynamicTablePage();
        double cpuCellValue = dynamicTablePage.getCpuLoadValue("Chrome");
        double cpuLabelValue = dynamicTablePage.getCpuLabelValue();
        Assert.assertEquals(cpuCellValue, cpuLabelValue);
    }

    //Testing the overlapped Element page
    @Test
    public void overlappedElementPageTest() {
        homePage.goTo();
        homePage.goToOverlappedElementPage();
        overlappedElementPage.setNameFieldValue("Test");
        String addedText = overlappedElementPage.getNameFieldValue();
        Assert.assertEquals(addedText, "Test");
    }

    //Testing the sample app page
    @Test
    public void sampleAppPageTest() {
        homePage.goTo();
        homePage.goToSampleAppPage();
        sampleAppPage.setUserName("Test");
        sampleAppPage.setPassWord("pwd");
        sampleAppPage.login();
        Assert.assertTrue(sampleAppPage.getLoginSuccessMessage().contains("Welcome"));
    }

    //Testing the dynamic ID page
    @Test
    public void dynamicIdPageTest() {
        homePage.goTo();
        homePage.goToDynamicIdPage();
        String btnIDValueBeforeRefresh = dynamicIdPage.getBtnIDAttribute();
        dynamicIdPage.clickDynamicBtn();
        String btnIDTextAfterFirstClick = dynamicIdPage.getBtnIdText();
        refreshWebPage();
        String btnIDValueafterRefersh = dynamicIdPage.getBtnIDAttribute();
        dynamicIdPage.clickDynamicBtn();
        String btnIDTextAfterSecondClick = dynamicIdPage.getBtnIdText();
        Assert.assertNotEquals(btnIDValueBeforeRefresh, btnIDValueafterRefersh);
        Assert.assertEquals(btnIDTextAfterFirstClick, btnIDTextAfterSecondClick);
    }
}
