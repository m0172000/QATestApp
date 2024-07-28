package TestReport;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import CommonUtlis.BaseClass;
import CommonUtlis.ScreenshotUtil;

public class TestListener implements ITestListener {

    private WebDriver driver;

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        // Assuming the WebDriver instance is passed or set elsewhere
        Object testClass = result.getInstance();
        driver = ((BaseClass) testClass).getDriver(); // Assuming a BaseTest class with a getDriver() method
        ScreenshotUtil.captureScreenshot(driver, result.getName());
    }

}