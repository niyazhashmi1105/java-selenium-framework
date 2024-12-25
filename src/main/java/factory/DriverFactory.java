package factory;

import config.ConfigReader;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import utils.ExceptionHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupLocalDriver() {
        WebDriver driver;
        String browserName = ConfigReader.getBrowser();
        browserName = browserName.toUpperCase();
        boolean isHeadless = ConfigReader.isHeadless();
        switch (browserName) {
            case "CHROME":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized"); // Open browser in maximized mode
                if (isHeadless) {
                    chromeOptions.addArguments("--headless", "--disable-gpu");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
                case "FIREFOX":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                    case "EDGE":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        if (isHeadless) {
                            edgeOptions.addArguments("--headless");
                        }
                        driver = new EdgeDriver(edgeOptions);
                        break;

                    case "SAFARI":
                        if (!isMacOS()) {
                            throw new UnsupportedOperationException("Safari is only supported on macOS.");
                        }
                        SafariOptions safariOptions = new SafariOptions();
                        driver = new SafariDriver(safariOptions);
                        break;

                    default:
                        throw new IllegalArgumentException("Unsupported browser type: " + browserName);
                }

            // Store the driver in ThreadLocal for thread-safe execution
            driverThreadLocal.set(driver);
    }

    private static void setupBrowserStack() throws MalformedURLException {
        MutableCapabilities browserStackOptions = new MutableCapabilities();

        // BrowserStack-specific capabilities
        browserStackOptions.setCapability("browserName", ConfigReader.getBrowser());
        browserStackOptions.setCapability("browserVersion", ConfigReader.getBrowserVersion());
        browserStackOptions.setCapability("bstack:options", Map.of(
                "os", ConfigReader.getBrowserstackOSPlatform(),
                "osVersion", ConfigReader.getBrowserstackOSVersion(),
                "projectName", "Selenium Test Automation",
                "buildName", "Build-" + System.currentTimeMillis(),
                "sessionName", "Test-" + System.currentTimeMillis(),
                "seleniumVersion", "4.10.0" // Specify the Selenium version to use
        ));

        WebDriver driver = new RemoteWebDriver(
                new URL("https://" + ConfigReader.getBrowserstackUsername() + ":" +
                        ConfigReader.getBrowserstackAccessKey() + "@hub-cloud.browserstack.com/wd/hub"),
                browserStackOptions);
        driverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        String environment = ConfigReader.getEnvironment();
        if (driverThreadLocal.get() == null) {

            try {
                if ("browserstack".equalsIgnoreCase(environment)) {
                   setupBrowserStack();
                } else {
                    setupLocalDriver();
                }
            } catch (Exception e) {
                ExceptionHandler.handleException("Failed to initialize WebDriver: " ,e);
            }
        }
        return driverThreadLocal.get();
    }
    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
    private static boolean isMacOS() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

}
