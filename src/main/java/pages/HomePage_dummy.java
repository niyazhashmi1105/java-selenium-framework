package pages;

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class HomePage_dummy {

    private final WebDriver driver;
    private final ElementActions elementActions;

    private final By logoutButton = By.xpath("//i[@class='icon-2x icon-signout']");
    private final By textAfterLogout = By.xpath("//div[@id='flash' and contains(text(),'You logged out of the secure area!')]");

    public HomePage_dummy(WebDriver driver){
        this.driver = DriverFactory.getDriver();
        this.elementActions = new ElementActions(driver);
    }

    public boolean isLogoutButtonDisplayed() {
        return elementActions.isDisplayed(DriverFactory.getDriver().findElement(logoutButton));
    }
    public void clickLogoutButton(){
        elementActions.click(DriverFactory.getDriver().findElement(logoutButton));
    }

    public String getText(){
        return elementActions.getText(DriverFactory.getDriver().findElement(textAfterLogout)).trim();
    }

}
