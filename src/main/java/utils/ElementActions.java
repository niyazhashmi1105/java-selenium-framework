package utils;

import com.aventstack.extentreports.Status;
import config.ConfigReader;
import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Base64;
import java.util.List;

import static factory.DriverFactory.getDriver;

public class ElementActions {

    protected final WebDriver driver;
    protected WebDriverWait wait;

    public ElementActions(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigReader.getMediumWaitTimeOut())); // 10-second timeout
    }

    // Method to navigate to a specific URL
    public void navigateTo(String url) {
        DriverFactory.getDriver().get(url);
        ExtentTestManager.getTest().log(Status.INFO,"Navigate to the URL"+url);

    }

    // Method to wait for an element to be visible
    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Method to wait for an element to be clickable
    protected WebElement waitForClickAbility(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForVisibilityOfElementLocated(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Method to click on an element
    public void click(WebElement element) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicked on an element ");
            waitForClickAbility(element).click();
        }catch(Exception e){
            ExceptionHandler.handleWebDriverException("Clicking on element: ", e);
        }
    }

    // Method to send text to an input field
    public void sendKeys(WebElement element, String text) {

        try {
//            if (text.contains("Password")) {
//                String encryptedPassword = PasswordEncryption.encrypt(text);
//                ExtentTestManager.getTest().log(Status.INFO, "Entering the value " + encryptedPassword + " on the textbox " + element.getText());
//            } else {
//                ExtentTestManager.getTest().log(Status.INFO, "Entering the value " + text + " on the textbox " + element.getText());
//            }
            waitForClickAbility(element).sendKeys(text);
        }catch(NoSuchElementException e){
            ExceptionHandler.handleWebDriverException("Sending keys to an element: ", e);
        }
    }

    // Method to get text from an element
    public String getText(WebElement element) {
        String getTextValue = "";
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Get the Text from an element: " + element.getText());
            getTextValue = waitForVisibility(element).getText();
        }catch(Exception e){
            ExceptionHandler.handleWebDriverException("Failed to get text from an element: " + element.getText(), e);
        }
        return getTextValue;
    }

    // Method to check if an element is displayed
    public boolean isDisplayed(WebElement element) {
        try {
            ExtentTestManager.getTest().log(Status.INFO,"Checking an element "+element.getText()+" is displayed or not: " + element.isDisplayed());
            return waitForVisibility(element).isDisplayed();
        } catch (Exception e) {
            ExceptionHandler.handleWebDriverException("Failed to display an element " + element.getText(), e);
            return false;
        }
    }

    // Method to handle JavaScript execution
    public void executeJavaScript(String script, Object... args) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript(script, args);
        } catch (Exception e) {
            ExceptionHandler.handleWebDriverException("Failed to execute javascript " + script, e);
        }
    }

    // Method to scroll to an element
    public void scrollToElement(WebElement element) {
        try {
            executeJavaScript("arguments[0].scrollIntoView(true);", element);
            ExtentTestManager.getTest().log(Status.INFO, "Scrolling to an element: " + element.getText());
        }catch(Exception e){
            ExceptionHandler.handleWebDriverException("Failed to scroll up to an element " + element.getText(), e);
        }
    }

    // Method to perform hover action
    public void hoverOverElement(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(waitForVisibility(element)).perform();
            ExtentTestManager.getTest().log(Status.INFO, "Mouse Hover over an element: " + element.getText());
        }catch(Exception e){
            ExceptionHandler.handleWebDriverException("Failed to mouse hover an element " + element.getText(), e);
        }
    }

    // Method to handle alerts
    public void acceptAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            ExtentTestManager.getTest().log(Status.INFO, "Accepting the alert");
        } catch (Exception e) {
            ExceptionHandler.handleWebDriverException("Failed to accept an alert ", e);
        }
    }

    public void dismissAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).dismiss();
            ExtentTestManager.getTest().log(Status.INFO, "Dismissing the alert");
        }catch(Exception e){
            ExceptionHandler.handleWebDriverException("Failed to dismiss an alert ", e);
        }
    }

    public String getAlertText() {
       Alert alertElement = null;
       String alertText;
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Getting the alert text");
            alertElement = wait.until(ExpectedConditions.alertIsPresent());
        }catch(Exception e){
            ExceptionHandler.handleWebDriverException("Failed to get an text from an alert ", e);
        }
        alertText = alertElement.getText();
        return alertText;
    }

   /* public String getPageTitle() {
        String pageTitle = "";
        WebElement pageTitleElement = null;
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Getting the Page Title");
            pageTitleElement = wait.until(ExpectedConditions.(pageTitle));
        }catch (Exception e){
            ExceptionHandler.handleWebDriverException("Failed to get an page title "+pageTitleElement.getText(), e);
        }
    }*/

    protected void getListOfWebElements(By locator, String text) {

        List<WebElement> elementsList = getDriver().findElements(locator);
        for(WebElement element:elementsList){
            if(element.getText().contains(text)){
                waitForClickAbility(element);
                element.click();
                break;
            }
       }
    }

    public void waitFor(long waiTime){
        try{
             Thread.sleep(waiTime * 100);
        }catch (Exception e){
            ExceptionHandler.handleException("Failed to wait for an element",e);
        }
    }

}
