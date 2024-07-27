package CommonUtlis;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;

public class BaseClass {
	public static AndroidDriver driver;

	public DataReadClass read = new DataReadClass();
	public Screnshot screenshot = new Screnshot();
	public static WebDriverWait wait;

	@BeforeSuite
	public void setUp() throws MalformedURLException, Throwable {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("appium:platformVersion", "11");
		desiredCapabilities.setCapability("appium:deviceName", "Redmi Note 8");
		desiredCapabilities.setCapability("appium:appPackage", "com.qatestapp");
		desiredCapabilities.setCapability("appium:appActivity", "com.qatestapp.SignIn");
		desiredCapabilities.setCapability("appium:noReset", false);
		desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
		desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
		desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
		desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
		desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), desiredCapabilities);
		wait = new WebDriverWait(driver, Duration.ofMinutes(2));
        Thread.sleep(1000);
	}

	public void restart() throws Throwable {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("appium:platformVersion", "11");
		desiredCapabilities.setCapability("appium:deviceName", "Redmi Note 8");
		desiredCapabilities.setCapability("appium:appPackage", "com.qatestapp");
		desiredCapabilities.setCapability("appium:appActivity", "com.qatestapp.SignIn");
		desiredCapabilities.setCapability("appium:noReset", false);
		desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), desiredCapabilities);
		wait = new WebDriverWait(driver, Duration.ofMinutes(2));
        Thread.sleep(1000);
	}
	//@AfterSuite
	public void trunoff() {
		
		driver.quit();
		// driver.removeApp(driver.getCurrentPackage());
	}
}
