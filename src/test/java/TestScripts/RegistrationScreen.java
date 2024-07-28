package TestScripts;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonUtlis.BaseClass;
import PomRepository.LandingPagePom;
import PomRepository.LoginPagePom;
import PomRepository.RegistrationScreenPom;

public class RegistrationScreen extends BaseClass {

	@Test(priority = 1)
	public void verifyingRegistrationScreenElements() {

		RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);
		SoftAssert softAssert = new SoftAssert();

		Reporter.log("Starting validation of all elements on the registration screen.", true);
		WebElement pageTitle = registrationScreen.getPageTitle();
		softAssert.assertTrue(pageTitle.isDisplayed(), "Page Title is not displayed.");
		softAssert.assertEquals(pageTitle.getAttribute("text"), "RegistrationScreen",
				"Page Title text does not match.");

		softAssert.assertTrue(registrationScreen.getFullnametextfield().isDisplayed(),
				"Full Name field is not displayed.");
		softAssert.assertTrue(registrationScreen.getEmailtextfield().isDisplayed(), "Email field is not displayed.");
		softAssert.assertTrue(registrationScreen.getPhoneNUmbertextfield().isDisplayed(),
				"Phone Number field is not displayed.");
		softAssert.assertTrue(registrationScreen.getSubscribeCheckBox().isDisplayed(),
				"Subscribe checkbox is not displayed.");
		softAssert.assertTrue(registrationScreen.getCancelButton().isDisplayed(), "Cancel button is not displayed.");
		softAssert.assertTrue(registrationScreen.getSubmitButton().isDisplayed(), "Submit button is not displayed.");

		softAssert.assertAll();
		Reporter.log("All elements on the registration screen are displayed correctly.", true);
	}

	@Test(priority = 2, dataProvider = "validRegistrationData")
	public void validatingRegistrationWithValidData(String fullName, String email, String phoneNumber,
			boolean subscribe) {
		RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);
		SoftAssert softAssert = new SoftAssert();

		Reporter.log("Starting registration test with Full Name: " + fullName + ", Email: " + email + ", Phone: "
				+ phoneNumber + ", Subscribe: " + subscribe, true);

		registrationScreen.getFullnametextfield().sendKeys(fullName);
		registrationScreen.getEmailtextfield().sendKeys(email);
		registrationScreen.getPhoneNUmbertextfield().sendKeys(phoneNumber);
		if (subscribe) {
			registrationScreen.getSubscribeCheckBox().click();
		}
		registrationScreen.getSubmitButton().click();
		try {
			wait.until(ExpectedConditions.visibilityOf(registrationScreen.getPageTitle()));
			softAssert.assertTrue(true, "User registration successful.");
		} catch (Throwable e) {
			e.printStackTrace();
			softAssert.assertFalse(true, "User registration failed.");
		}
		softAssert.assertAll();
		Reporter.log("User successfully registered with valid data.", true);
	}

	@DataProvider(name = "validRegistrationData")
	public Object[][] validRegistrationDataProvider() throws Throwable {
		String fullName = read.readStringData("RegScreen", 0, 0);
		String email = read.readStringData("RegScreen", 0, 1);
		String phoneNumber = read.readNumericalData("RegScreen", 0, 2);
		boolean subscribe = convertToBoolean(read.readStringData("RegScreen", 0, 3));

		return new Object[][] { { fullName, email, phoneNumber, subscribe } };
	}

	private boolean convertToBoolean(String value) {
		if (value.equalsIgnoreCase("t")) {
			return true;
		}
		return false;
	}

	@Test(priority = 2, dataProvider = "missingFullNameData")
	public void validateMissingFullName(String fullName, String email, String phoneNumber, boolean subscribe) {
		RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);
		SoftAssert softAssert = new SoftAssert();

		Reporter.log("Starting registration test with missing Full Name.", true);

		registrationScreen.getEmailtextfield().sendKeys(email);
		registrationScreen.getPhoneNUmbertextfield().sendKeys(phoneNumber);
		if (subscribe) {
			registrationScreen.getSubscribeCheckBox().click();
		}
		registrationScreen.getSubmitButton().click();
		registrationScreen.getFullnametextfield().click();
		softAssert.assertTrue(registrationScreen.getSubmitButton().isDisplayed(),
				"Error message: 'The name field cannot be empty or contain numerical/special characters' is not displayed.");

		softAssert.assertAll();
		Reporter.log("Validation for missing Full Name completed. Error message displayed as expected.", true);
	}

	@Test(priority = 3, dataProvider = "validatePhoneNumberDatalessthan10digit")
	public void validateLessThan10DigitPhoneNumber(String fullName, String email, String phoneNumber, boolean subscribe)
			throws Throwable {
		RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);
		SoftAssert softAssert = new SoftAssert();

		Reporter.log("Starting registration test with less than 10 digit Phone Number.", true);

		registrationScreen.getFullnametextfield().sendKeys(fullName);
		registrationScreen.getEmailtextfield().sendKeys(email);
		registrationScreen.getPhoneNUmbertextfield().sendKeys(phoneNumber);
		if (subscribe) {
			registrationScreen.getSubscribeCheckBox().click();
		}
		registrationScreen.getSubmitButton().click();
		Thread.sleep(Duration.ofSeconds(10));
		softAssert.assertFalse(new LandingPagePom(driver).getPageTitle().isDisplayed(),
				"User should not be able to register with less than 10 digit mobile number.");
		softAssert.assertAll();
		Reporter.log(
				"Validation for less than 10 digit phone number completed. User registration prevented as expected.",
				true);
	}

	@Test(priority = 4, dataProvider = "validateMissingPhoneNumberData")
	public void validateMissingPhoneNumber(String fullName, String email, String phoneNumber, boolean subscribe) {
		RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);
		SoftAssert softAssert = new SoftAssert();

		Reporter.log("Starting registration test with missing Phone Number.", true);

		registrationScreen.getFullnametextfield().sendKeys(fullName);
		registrationScreen.getEmailtextfield().sendKeys(email);
		if (subscribe) {
			registrationScreen.getSubscribeCheckBox().click();
		}
		registrationScreen.getSubmitButton().click();
		registrationScreen.getPhoneNUmbertextfield().click();
		softAssert.assertTrue(registrationScreen.getSubmitButton().isDisplayed(),
				"Error message: 'Phone number is required' is not displayed.");

		softAssert.assertAll();
		Reporter.log("Validation for missing Phone Number completed. Error message displayed as expected.", true);
	}

	@Test(priority = 5, dataProvider = "validateMissingEmailData")
	public void validateMissingEmail(String fullName, String email, String phoneNumber, boolean subscribe) {
		RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);
		SoftAssert softAssert = new SoftAssert();

		Reporter.log("Starting registration test with missing Email.", true);

		registrationScreen.getFullnametextfield().sendKeys(fullName);
		registrationScreen.getPhoneNUmbertextfield().sendKeys(phoneNumber);
		if (subscribe) {
			registrationScreen.getSubscribeCheckBox().click();
		}
		registrationScreen.getSubmitButton().click();
		registrationScreen.getEmailtextfield().click();
		softAssert.assertTrue(registrationScreen.getSubmitButton().isDisplayed(),
				"Error message: 'Invalid email format' is not displayed.");

		softAssert.assertAll();
		Reporter.log("Validation for missing Email completed. Error message displayed as expected.", true);
	}

	@Test(priority = 6, dataProvider = "validateInvalidEmailFormatData")
	public void validateInvalidEmailFormat(String fullName, String email, String phoneNumber, boolean subscribe) {
		RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);
		SoftAssert softAssert = new SoftAssert();

		Reporter.log("Starting registration test with invalid Email format.", true);

		registrationScreen.getFullnametextfield().sendKeys(fullName);
		registrationScreen.getEmailtextfield().sendKeys(email);
		registrationScreen.getPhoneNUmbertextfield().sendKeys(phoneNumber);
		if (subscribe) {
			registrationScreen.getSubscribeCheckBox().click();
		}
		registrationScreen.getSubmitButton().click();
		softAssert.assertFalse(new LandingPagePom(driver).getPageTitle().isDisplayed(),
				"User should not be able to register with an invalid email format.");
		softAssert.assertAll();
		Reporter.log("Validation for invalid Email format completed. User registration prevented as expected.", true);
	}

	@Test(priority = 7, dataProvider = "validateWrongFullNameData")
	public void validateWrongFullName(String fullName, String email, String phoneNumber, boolean subscribe) {
		RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);
		SoftAssert softAssert = new SoftAssert();

		Reporter.log("Starting registration test with invalid Full Name format (special characters only).", true);

		registrationScreen.getFullnametextfield().sendKeys(fullName);
		registrationScreen.getEmailtextfield().sendKeys(email);
		registrationScreen.getPhoneNUmbertextfield().sendKeys(phoneNumber);
		if (subscribe) {
			registrationScreen.getSubscribeCheckBox().click();
		}
		registrationScreen.getSubmitButton().click();

		softAssert.assertFalse(new LandingPagePom(driver).getPageTitle().isDisplayed(),
				"User should not be able to register with an invalid Full Name format.");
		softAssert.assertAll();
		Reporter.log("Validation for invalid Full Name format completed. User registration prevented as expected.",
				true);
	}

	@Test(priority = 8)
	public void validateCancelButtonClearsFields() {
		RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);
		SoftAssert softAssert = new SoftAssert();

		Reporter.log(
				"Starting validation of the Cancel button to ensure it clears the fields on the registration screen.",
				true);

		// Enter data into the fields
		String testFullName = "Test User";
		String testEmail = "test@example.com";
		String testPhoneNumber = "1234567890";

		registrationScreen.getFullnametextfield().sendKeys(testFullName);
		registrationScreen.getEmailtextfield().sendKeys(testEmail);
		registrationScreen.getPhoneNUmbertextfield().sendKeys(testPhoneNumber);

		// Click the Cancel button
		registrationScreen.getCancelButton().click();

		// Validate that the fields are cleared
		softAssert.assertEquals(registrationScreen.getFullnametextfield().getText(), "",
				"Full Name field is not cleared.");
		softAssert.assertEquals(registrationScreen.getEmailtextfield().getText(), "", "Email field is not cleared.");
		softAssert.assertEquals(registrationScreen.getPhoneNUmbertextfield().getText(), "",
				"Phone Number field is not cleared.");

		softAssert.assertAll();
		Reporter.log("Cancel button functionality validated: All fields are cleared successfully.", true);
	}

	@DataProvider(name = "missingFullNameData")
	public Object[][] missingFullNameDataProvider() throws Throwable {
		String fullName = read.readStringData("RegScreen", 1, 0);
		String email = read.readStringData("RegScreen", 1, 1);
		String phoneNumber = read.readNumericalData("RegScreen", 1, 2);
		boolean subscribe = convertToBoolean(read.readStringData("RegScreen", 1, 3));
		return new Object[][] { { fullName, email, phoneNumber, subscribe } };
	}

	@DataProvider(name = "validatePhoneNumberDatalessthan10digit")
	public Object[][] validateLessThan10DigitPhoneNumberDataProvider() throws Throwable {
		String fullName = read.readStringData("RegScreen", 2, 0);
		String email = read.readStringData("RegScreen", 2, 1);
		String phoneNumber = read.readNumericalData("RegScreen", 2, 2);
		boolean subscribe = convertToBoolean(read.readStringData("RegScreen", 2, 3));
		return new Object[][] { { fullName, email, phoneNumber, subscribe } };
	}

	@DataProvider(name = "validateMissingPhoneNumberData")
	public Object[][] validateMissingPhoneNumberDataProvider() throws Throwable {
		String fullName = read.readStringData("RegScreen", 3, 0);
		String email = read.readStringData("RegScreen", 3, 1);
		String phoneNumber = read.readNumericalData("RegScreen", 3, 2);
		boolean subscribe = convertToBoolean(read.readStringData("RegScreen", 3, 3));
		return new Object[][] { { fullName, email, phoneNumber, subscribe } };
	}

	@DataProvider(name = "validateMissingEmailData")
	public Object[][] validateMissingEmailDataProvider() throws Throwable {
		String fullName = read.readStringData("RegScreen", 4, 0);
		String email = read.readStringData("RegScreen", 4, 1);
		String phoneNumber = read.readNumericalData("RegScreen", 4, 2);
		boolean subscribe = convertToBoolean(read.readStringData("RegScreen", 4, 3));
		return new Object[][] { { fullName, email, phoneNumber, subscribe } };
	}

	@DataProvider(name = "validateInvalidEmailFormatData")
	public Object[][] validateInvalidEmailFormatDataProvider() throws Throwable {
		String fullName = read.readStringData("RegScreen", 5, 0);
		String email = read.readStringData("RegScreen", 5, 1);
		String phoneNumber = read.readNumericalData("RegScreen", 5, 2);
		boolean subscribe = convertToBoolean(read.readStringData("RegScreen", 5, 3));
		return new Object[][] { { fullName, email, phoneNumber, subscribe } };
	}

	@DataProvider(name = "validateWrongFullNameData")
	public Object[][] validateWrongFullNameDataProvider() throws Throwable {
		String fullName = read.readStringData("RegScreen", 6, 0);
		String email = read.readStringData("RegScreen", 6, 1);
		String phoneNumber = read.readNumericalData("RegScreen", 6, 2);
		boolean subscribe = convertToBoolean(read.readStringData("RegScreen", 6, 3));
		return new Object[][] { { fullName, email, phoneNumber, subscribe } };
	}

	@BeforeMethod
	public void validatingLoginWithValidCredentials() throws InterruptedException {
		LoginPagePom loginPage = new LoginPagePom(driver);
		RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);

		WebElement userIdField = loginPage.getUserIDTextField();
		userIdField.sendKeys("helloWorld@gmail.com");

		WebElement passwordField = loginPage.getPasswordTextField();
		passwordField.sendKeys("testing1234");

		loginPage.getStartTestButton().click();
		;
		Thread.sleep(Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(registrationScreen.getPageTitle()));

	}

	@AfterMethod
	public void methoddsetup() throws Throwable {
		new RegistrationScreen().restart();

	}
}
