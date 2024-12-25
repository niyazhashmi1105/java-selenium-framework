package listeners;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import factory.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentTestManager;
import utils.ReportParser;

import java.awt.*;
import java.io.File;

public class TestListener  implements ITestListener, IExecutionListener {


    @Override
    public void onExecutionStart() {}

    @Override
    public void onExecutionFinish() {
        ReportParser.parseHTMLReport(System.getProperty("user.dir") + "/reports/index.html");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getTestClass().getName(), result.getMethod().getMethodName());
        ExtentTestManager.getTest().log(Status.INFO, result.getMethod().getMethodName() + " test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, result.getMethod().getMethodName() + " test execution passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        Exception exception = (Exception) result.getThrowable();
        String errorMessage = "Test failed: " + result.getMethod().getMethodName();
        Object testClass = result.getInstance();


        if (testClass instanceof BaseTest baseTest) {
            WebDriver driver = DriverFactory.getDriver();

            // Check if the driver instance is valid and supports screenshot capture
            if (driver instanceof TakesScreenshot) {
                String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

                // Log failure details and attach screenshot
                ExtentTestManager.getTest()
                        .log(Status.FAIL ,errorMessage ,result.getThrowable(),
                                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            }
        }
}


    @Override
    public  void onTestSkipped(ITestResult result) {

        System.out.println(result.getName() + " test execution skipped.");
        ExtentTestManager.getTest().log(Status.SKIP, result.getName()+" test execution skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public  void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public  void onStart(ITestContext context) {
    }

    @Override
    public  void onFinish(ITestContext context) {
        ExtentTestManager.endTest();
        openExtentReport("reports/index.html");
    }

    private static void openExtentReport(String filePath) {
        try {
            File reportFile = new File(filePath);
            if (reportFile.exists()) {
                Desktop.getDesktop().open(reportFile); // Opens the report with default browser
            } else {
                System.out.println("Report file does not exist.");
            }
        } catch (Exception e) {
            System.err.println("Unable to open report: " + e.getMessage());
        }
    }
}
