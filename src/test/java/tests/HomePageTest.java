package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    public HomePageTest(){
        super();
    }

    @Test
    public void verifyIsLogoutButtonDisplayed(){
        loginPage.openURL(ConfigReader.getBaseUrl());
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickOnLoginButton();
        boolean status = homePage.isLogoutButtonDisplayed();
        assert status;
    }

    @Test
    public void verifyTextAfterLogout(){
        loginPage.openURL(ConfigReader.getBaseUrl());
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickOnLoginButton();
        homePage.clickLogoutButton();
        String actualText = homePage.getText();
        System.out.println(actualText);
        Assert.assertTrue(actualText.contains("You logged out"),
                "The actual text does not contain the expected substring.");

    }
}
