package tests;

import base.BaseTest;
import config.ConfigReader;
import factory.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    public LoginPageTest() {
        super();
    }

    @Test
    public void test1_dev(){

        loginPage.openURL(ConfigReader.getBaseUrl());
        System.out.println("Page Title: " + DriverFactory.getDriver().getTitle());
    }
    @Test
    public void test2_dev(){

        loginPage.openURL(ConfigReader.getBaseUrl());
        System.out.println("Page Title: " + DriverFactory.getDriver().getTitle());
    }

    @Test
    public void test3_dev(){

        loginPage.openURL(ConfigReader.getBaseUrl());
        System.out.println("Page Title: " + DriverFactory.getDriver().getTitle());
    }

    @Test
    public void verifyPageTitleAfterLogin(){

        loginPage.openURL(ConfigReader.getBaseUrl());
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        loginPage.clickOnLoginButton();

        // Perform test actions
        Assert.assertEquals(DriverFactory.getDriver().getTitle(),"The Internet");
    }

    @Test
    public void verifyIsLoginBtnDisplayed(){

        loginPage.openURL(ConfigReader.getBaseUrl());
        boolean status = loginPage.isLoginButtonDisplayed();
        Assert.assertTrue(status);
    }

    @Test
    public void verifyPageTitleAfterSuccessfulLogin(){

        loginPage.openURL(ConfigReader.getBaseUrl());
        loginPage.enterCredentials("tomsmith","SuperSecretPassword!");
        boolean status = loginPage.isLoginButtonDisplayed();
        Assert.assertTrue(status);
        loginPage.clickOnLoginButton();

        // Perform test actions
        Assert.assertEquals(DriverFactory.getDriver().getTitle(),"The Internet");
    }
}
