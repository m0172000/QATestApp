package TestScripts;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonUtlis.BaseClass;
import PomRepository.LoginPagePom;
import PomRepository.RegistrationScreenPom;

public class loginPage extends BaseClass {
  
	 @Test(priority = 1)
	    public void VerifyingLoginPage() {
	        LoginPagePom loginPage = new LoginPagePom(driver);
	        PageFactory.initElements(driver, loginPage);
	        SoftAssert softAssert = new SoftAssert();

	        Reporter.log("Starting validation of all elements and their texts.", true);

	        WebElement qATestAppTitle = loginPage.getQATestAppTitle();
	        boolean isQATestAppTitleDisplayed = qATestAppTitle.isDisplayed();
	        softAssert.assertTrue(isQATestAppTitleDisplayed, "QATestApp Title is not displayed.");

	        WebElement userIdField = loginPage.getUserIDTextField();
	        boolean isUserIdFieldDisplayed = userIdField.isDisplayed();
	        softAssert.assertTrue(isUserIdFieldDisplayed, "User ID field is not displayed.");

	        WebElement passwordField = loginPage.getPasswordTextField();
	        boolean isPasswordFieldDisplayed = passwordField.isDisplayed();
	        softAssert.assertTrue(isPasswordFieldDisplayed, "Password field is not displayed.");

	        WebElement eyeIcon = loginPage.getEyeIcon();
	        boolean isEyeIconDisplayed = eyeIcon.isDisplayed();
	        softAssert.assertTrue(isEyeIconDisplayed, "Eye icon is not displayed.");

	        WebElement startTestButton = loginPage.getStartTestButton();
	        boolean isStartTestButtonDisplayed = startTestButton.isDisplayed();
	        softAssert.assertTrue(isStartTestButtonDisplayed, "Start Test button is not displayed.");

	        WebElement subtitle = loginPage.getSubtitle();
	        boolean isSubtitleDisplayed = subtitle.isDisplayed();
	        softAssert.assertTrue(isSubtitleDisplayed, "Subtitle is not displayed.");

	        WebElement instructionDetailsText = loginPage.getInstructionDetailsText();
	        boolean isInstructionDetailsTextDisplayed = instructionDetailsText.isDisplayed();
	        softAssert.assertTrue(isInstructionDetailsTextDisplayed, "Instruction Details Text is not displayed.");

	        String qATestAppTitleText = qATestAppTitle.getText();
	        softAssert.assertEquals(qATestAppTitleText, "QATestApp", "QATestApp Title text does not match.");

	        String userIdText = userIdField.getText();
	        softAssert.assertEquals(userIdText, "User ID *", "User ID field text does not match.");

	        String passwordText = passwordField.getText();
	        softAssert.assertEquals(passwordText, "Password *", "Password field text does not match.");

	        String startTestButtonText = startTestButton.getText();
	        softAssert.assertEquals(startTestButtonText, "START TEST", "Start Test button text does not match.");

	        String subtitleText = subtitle.getText();
	        softAssert.assertEquals(subtitleText, "INSTRUCTIONS", "Subtitle text does not match.");

	        String instructionDetailsTextContent = instructionDetailsText.getText();
	        softAssert.assertNotNull(instructionDetailsTextContent, "Instruction Details Text content is not present.");

	        softAssert.assertAll();
	        Reporter.log("All elements are displayed and texts are correct.", true);
	    }

	    @Test(dataProvider = "validLoginData", priority = 2)
	    public void validatingLoginWithValidCredentials(String userID, String password) throws InterruptedException {
	        LoginPagePom loginPage = new LoginPagePom(driver);
	        RegistrationScreenPom registrationScreen = new RegistrationScreenPom(driver);
	        SoftAssert softAssert = new SoftAssert();

	        Reporter.log("Starting test with UserID: " + userID + " and Password: " + password, true);

	        WebElement userIdField = loginPage.getUserIDTextField();
	        Reporter.log("Entering UserID: " + userID, true);
	        userIdField.sendKeys(userID);

	        WebElement passwordField = loginPage.getPasswordTextField();
	        Reporter.log("Entering Password: [PROTECTED]", true);
	        passwordField.sendKeys(password);

	        WebElement startTestButton = loginPage.getStartTestButton();
	        Reporter.log("Clicking the Start Test button", true);
	        startTestButton.click();
             Thread.sleep(Duration.ofSeconds(10));
	        Reporter.log("Waiting for the Registration Screen to load...", true);
	        wait.until(ExpectedConditions.visibilityOf(registrationScreen.getPageTitle()));

	        String actualText1 = registrationScreen.getPageTitle().getText();
	        String expectedText1 = "RegistrationScreen";
	        Reporter.log("Actual Registration Screen Title: " + actualText1, true);
	        Reporter.log("Expected Registration Screen Title: " + expectedText1, true);

	        softAssert.assertEquals(actualText1, expectedText1, "Registration Screen title does not match!");

	        Reporter.log("User logged into the application successfully with valid credentials.", true);
	        softAssert.assertAll();
	    }

	    @Test(dataProvider = "invalidLoginData", priority = 3)
	    public void validatingLoginWithInvalidCredentials(String userID, String password) {
	        LoginPagePom loginPage = new LoginPagePom(driver);
	        PageFactory.initElements(driver, loginPage);
	        SoftAssert softAssert = new SoftAssert();

	        Reporter.log("Starting test with invalid UserID: " + userID + " and Password: " + password, true);

	        WebElement userIdField = loginPage.getUserIDTextField();
	        Reporter.log("Entering invalid UserID: " + userID, true);
	        userIdField.sendKeys(userID);

	        WebElement passwordField = loginPage.getPasswordTextField();
	        Reporter.log("Entering invalid Password: [PROTECTED]", true);
	        passwordField.sendKeys(password);

	        WebElement startTestButton = loginPage.getStartTestButton();
	        Reporter.log("Clicking the Start Test button", true);
	        startTestButton.click();

	        boolean isButtonDisplayed = startTestButton.isDisplayed();
	        softAssert.assertTrue(isButtonDisplayed, "The Start Test button is not displayed. This may indicate a login was successful, which is unexpected.");

	        Reporter.log("Test passed: User was not able to log in with invalid credentials, as expected.", true);
	        softAssert.assertAll();
	    }

	    @Test(dataProvider = "passwordVisibilityData", priority = 5)
	    public void validatingPasswordVisibilityToggle(String userID, String password) {
	        LoginPagePom loginPage = new LoginPagePom(driver);
	        PageFactory.initElements(driver, loginPage);
	        SoftAssert softAssert = new SoftAssert();

	        Reporter.log("Starting test for password visibility toggle.", true);

	        WebElement passwordField = loginPage.getPasswordTextField();
	        WebElement eyeIcon = loginPage.getEyeIcon(); // Ensure this method returns the eye icon WebElement

	        passwordField.clear();
	        passwordField.sendKeys(password);
	        Reporter.log("Entered password.", true);

	        wait.until(ExpectedConditions.visibilityOf(passwordField));

	        eyeIcon.click();
	        Reporter.log("Clicked eye icon to reveal password.", true);

	        wait.until(ExpectedConditions.visibilityOf(passwordField));

	        String isCheckedAfterReveal = eyeIcon.getAttribute("checked");
	        softAssert.assertEquals(isCheckedAfterReveal, "true", "Password visibility toggle failed: Password is not revealed correctly.");

	        eyeIcon.click();
	        Reporter.log("Clicked eye icon to hide password.", true);

	        wait.until(ExpectedConditions.visibilityOf(passwordField));

	        String isCheckedAfterHide = eyeIcon.getAttribute("checked");
	        softAssert.assertEquals(isCheckedAfterHide, "false", "Password hiding toggle failed: Password is still visible.");

	        Reporter.log("Test passed: Password visibility toggle is working correctly.", true);
	        softAssert.assertAll();
	    }
	
	
	 @Test(dataProvider = "bothFieldsEmptyData", priority=4)
	    public void validatingLoginWithBothFieldsEmpty(String userID, String password) {
	        LoginPagePom loginPage = new LoginPagePom(driver);
	        PageFactory.initElements(driver, loginPage);

	        WebElement userIdField = loginPage.getUserIDTextField();
	        WebElement passwordField = loginPage.getPasswordTextField();
	        WebElement startTestButton = loginPage.getStartTestButton();

	        userIdField.clear();
	        passwordField.clear();
	        startTestButton.click();

	        try {
	            Thread.sleep(10000);
	        } catch (InterruptedException e) {
	            Reporter.log("Interrupted Exception occurred during sleep: " + e.getMessage(), true);
	        }

	        boolean isStartTestButtonDisplayed = startTestButton.isDisplayed();
	        Assert.assertTrue(isStartTestButtonDisplayed, "Start Test button should be displayed after clicking with empty fields.");
	        Reporter.log("The Email cannot be empty error message is displayed when user id and email field is empty.", true);
	    }

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginDataProvider() throws Throwable {
        String userID = read.readStringData("LoginData", 1, 0);
        String password = read.readStringData("LoginData", 1, 1);
        return new Object[][] {
            {userID, password}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginDataProvider() throws Throwable {
        String userID = read.readStringData("LoginData", 2, 0);
        String password = read.readStringData("LoginData", 2, 1);
        return new Object[][] {
            {userID, password}
        };
    }

    @DataProvider(name = "bothFieldsEmptyData")
    public Object[][] bothFieldsEmptyDataProvider() throws Throwable {
        String userID = read.readStringData("LoginData", 5, 0);
        String password = read.readStringData("LoginData", 5, 1);
        return new Object[][] {
            {userID, password}
        };
    }

    @DataProvider(name = "passwordVisibilityData")
    public Object[][] passwordVisibilityDataProvider() throws Throwable {
        String userID = read.readStringData("LoginData", 6, 0);
        String password = read.readStringData("LoginData", 6, 1);
        return new Object[][] {
            {userID, password}
        };
    }
    
    @AfterMethod
    public void tearDown() throws Throwable {
        // Restart the app to ensure a fresh state for the next test
        new loginPage().restart();
    }

}
