package PomRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class LandingPagePom {

	@FindBy(xpath = "//android.widget.TextView[@text=\"LandingPage\"]")
	private WebElement PageTitle;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.qatestapp:id/landingText\"]")
	private WebElement PageContentText;
	
	public LandingPagePom(AndroidDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getPageTitle() {
		return PageTitle;
	}

	public WebElement getPageContentText() {
		return PageContentText;
	}
	
}
