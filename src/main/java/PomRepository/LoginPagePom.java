package PomRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class LoginPagePom {

	@FindBy(xpath = "//android.widget.TextView[@text=\"QATestApp\"]")
	private WebElement QATestAppTitle;
	
	@FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.qatestapp:id/emailTextField1\"]")
	private WebElement UserIDTextField;
	
	@FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.qatestapp:id/passwordTextField\"]")
	private WebElement PasswordTextField;
	
	@FindBy(xpath = "//android.widget.ImageButton[@resource-id=\"com.qatestapp:id/text_input_end_icon\"]")
	private WebElement EyeIcon;
	
	@FindBy(xpath = "//android.widget.Button[@resource-id=\"com.qatestapp:id/LoginButton\"]")
	private WebElement StartTestButton;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"INSTRUCTIONS\"]")
	private WebElement subtitle;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.qatestapp:id/layout6\"]")
	private WebElement InstructionDetailsText;
	
	public LoginPagePom(AndroidDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getQATestAppTitle() {
		return QATestAppTitle;
	}

	public WebElement getUserIDTextField() {
		return UserIDTextField;
	}

	public WebElement getPasswordTextField() {
		return PasswordTextField;
	}
	
	public WebElement getEyeIcon() {
		return EyeIcon;
	}
	public WebElement getStartTestButton() {
		return StartTestButton;
	}

	public WebElement getSubtitle() {
		return subtitle;
	}

	public WebElement getInstructionDetailsText() {
		return InstructionDetailsText;
	}
		
}
