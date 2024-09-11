package PomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class LoginPagePom {

	@FindBy(xpath = "/html/body/div/div[1]/div/div/div/div/a/strong")
	private WebElement SignInDiscord;
	
	@FindBy(id = "username")
	private WebElement EmailTextField;
	
	@FindBy(id = "password")
	private WebElement PasswordTextField;
	
	@FindBy(xpath = "//button[text()=\"Log In\"]")
	private WebElement LoginButton;
	
	@FindBy(xpath = "//a[text()=\"Forgot Password?\"]")
	private WebElement ForgotlinkTest;
	
	@FindBy(xpath = "/html/body/div/div[1]/div/div/div/form/p[2]/small/a")
	private WebElement SigupLinktext;
	

	public LoginPagePom(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getSignInDiscord() {
		return SignInDiscord;
	}

	public WebElement getEmailTextField() {
		return EmailTextField;
	}

	public WebElement getPasswordTextField() {
		return PasswordTextField;
	}

	public WebElement getLoginButton() {
		return LoginButton;
	}

	public WebElement getForgotlinkTest() {
		return ForgotlinkTest;
	}

	public WebElement getSigupLinktext() {
		return SigupLinktext;
	}
		
}
