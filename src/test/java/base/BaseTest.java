package base;

import factory.DriverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.FADavisHomePage;
import pages.HomePage_dummy;
import pages.LoginPage_dummy;
import utils.ElementActions;

public class BaseTest {

    protected WebDriver driver;
    public ElementActions elementActions;
    protected LoginPage_dummy loginPage;
    protected HomePage_dummy homePage;
    protected FADavisHomePage faDavisHomePage;

    public BaseTest() {}

    @BeforeClass
    public void setup(){
        driver = DriverFactory.getDriver();
        elementActions = new ElementActions(driver);
        loginPage = new LoginPage_dummy(driver);
        homePage = new HomePage_dummy(driver);
        faDavisHomePage = new FADavisHomePage(driver);
    }

    @AfterClass
    public void tearDown(){
        DriverFactory.quitDriver();
    }

}

