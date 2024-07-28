package PomRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class RegistrationScreenPom {

	@FindBy(xpath = "//android.widget.TextView[@text=\"RegistrationScreen\"]")
	private WebElement PageTitle;

	@FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.qatestapp:id/nameTextField\"]")
	private WebElement Fullnametextfield;

	@FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.qatestapp:id/emailTextField\"]")
	private WebElement Emailtextfield;

	@FindBy(xpath = "//android.widget.EditText[@resource-id=\"com.qatestapp:id/phoneNumberTextField\"]")
	private WebElement PhoneNUmbertextfield;

	@FindBy(xpath = "//android.widget.CheckBox[@resource-id=\"com.qatestapp:id/subscribeCheckbox\"]")
	private WebElement SubscribeCheckBox;

	@FindBy(xpath = "//android.widget.Button[@resource-id=\"com.qatestapp:id/button1\"]")
	private WebElement cancelButton;

	@FindBy(xpath = "//android.widget.Button[@resource-id=\"com.qatestapp:id/button2\"]")
	private WebElement SubmitButton;

	public RegistrationScreenPom(AndroidDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getPageTitle() {
		return PageTitle;
	}

	public WebElement getFullnametextfield() {
		return Fullnametextfield;
	}

	public WebElement getEmailtextfield() {
		return Emailtextfield;
	}

	public WebElement getPhoneNUmbertextfield() {
		return PhoneNUmbertextfield;
	}

	public WebElement getSubscribeCheckBox() {
		return SubscribeCheckBox;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public WebElement getSubmitButton() {
		return SubmitButton;
	}

}
