package pages;

import config.ConfigReader;
import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementActions;
import utils.ExceptionHandler;

import static factory.DriverFactory.getDriver;

public class FADavisHomePage {

    private final WebDriver driver;
    private final ElementActions elementActions;

    public FADavisHomePage(WebDriver driver){
        this.driver = DriverFactory.getDriver();
        this.elementActions = new ElementActions(driver);
    }

    private final By instructor = By.xpath("//img[@src='../../assets/images/Instructor.svg']");
    private final By student = By.xpath("//img[@src='../../assets/images/Student.svg']");
    private final By loginBtnLink = By.xpath("(//a[@role='menuitem' and contains(text(),'Log In')])[2]");
    private final By agreeContinueBtn = By.xpath("//button[contains(text(),'Agree & Continue')]");
    private final By usernameElement = By.xpath("//input[@id='UserName']");
    private final By passwordElement = By.xpath("//input[@id='Password']");
    private final By loginBtnElement = By.xpath("//button[@id='btnLogin']");

    public void openURL(String URL){
        elementActions.navigateTo(URL);
    }

    public void clickOnInstructor(){
        WebElement instructorIconElement = getDriver().findElement(instructor);
        elementActions.click(instructorIconElement);
    }

    public void clickOnAgreeContinueBtn(){
        WebElement agreeContinueBtnElement = getDriver().findElement(agreeContinueBtn);
        if(elementActions.isDisplayed(agreeContinueBtnElement)) {
            elementActions.click(agreeContinueBtnElement);
        }
    }

    public void clickLoginBtn(){
        WebElement loginBtnElement = getDriver().findElement(loginBtnLink);
        elementActions.click(loginBtnElement);
    }

    public void doLogin(String userName, String password) {

        try {
            elementActions.waitFor(ConfigReader.getMediumWaitTimeOut());
            elementActions.sendKeys(DriverFactory.getDriver().findElement(By.xpath("//input[@id='UserName']")),userName);
            elementActions.sendKeys(DriverFactory.getDriver().findElement(By.xpath("//input[@id='Password']")),password);
            elementActions.click(DriverFactory.getDriver().findElement(loginBtnElement));
        }catch (Exception e){
            ExceptionHandler.handleException("Unable to login to application",e);
        }
    }
}
