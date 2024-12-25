package pages;

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class LoginPage_dummy {

    private final WebDriver driver;
    private final ElementActions elementActions;

    // Locators for elements on the login page
    private final By usernameField = By.cssSelector("#username");
    private final By passwordField = By.cssSelector("#password");
    private final By loginButton = By.xpath("//button[@type='submit']");


    public LoginPage_dummy(WebDriver driver){
         this.driver = DriverFactory.getDriver();
        this.elementActions = new ElementActions(driver);
    }

    public void openURL(String URL){
        elementActions.navigateTo(URL);
    }

    public void enterCredentials(String username, String password) {
        elementActions.sendKeys(DriverFactory.getDriver().findElement(usernameField), username);
        elementActions.sendKeys(DriverFactory.getDriver().findElement(passwordField), password);
    }

    public boolean isLoginButtonDisplayed() {
        return elementActions.isDisplayed(DriverFactory.getDriver().findElement(loginButton));
    }

    public void clickOnLoginButton() {
        elementActions.click(DriverFactory.getDriver().findElement(loginButton));
    }

}
