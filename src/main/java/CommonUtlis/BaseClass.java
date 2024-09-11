package CommonUtlis;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;

public class BaseClass {
	public static WebDriver driver;

	public DataReadClass read = new DataReadClass();
	public static WebDriverWait wait;

	@BeforeSuite
	public void setUp() throws MalformedURLException, Throwable {
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(20));
		driver.get("https://app.germanyiscalling.com/common/login/");
		wait=new WebDriverWait(driver, Duration.ofMinutes(1));
	}
	
	@AfterSuite
	public void setDown() {
		driver.quit();
	}

	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}

}
