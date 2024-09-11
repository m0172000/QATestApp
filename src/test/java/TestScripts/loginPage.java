package TestScripts;


import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.commons.math3.primes.Primes;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
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

public class loginPage extends BaseClass {

	@Test(priority = 1)
	public void verifyElementsVisibility() {
		SoftAssert softAssert = new SoftAssert();
		LoginPagePom LoginPom = new LoginPagePom(driver);
		Reporter.log("Starting visibility check of web elements", true);
		// Check the visibility of the 'Sign In with Discord' link
		boolean isSignInDiscordDisplayed = LoginPom.getSignInDiscord().isDisplayed();
		softAssert.assertTrue(isSignInDiscordDisplayed, "Sign In with Discord link is not displayed.");

		// Check the visibility of the Email Text Field
		boolean isEmailTextFieldDisplayed = LoginPom.getEmailTextField().isDisplayed();
		softAssert.assertTrue(isEmailTextFieldDisplayed, "Email Text Field is not displayed.");

		// Check the visibility of the Password Text Field
		boolean isPasswordTextFieldDisplayed = LoginPom.getPasswordTextField().isDisplayed();
		softAssert.assertTrue(isPasswordTextFieldDisplayed, "Password Text Field is not displayed.");

		// Check the visibility of the Login Button
		boolean isLoginButtonDisplayed = LoginPom.getLoginButton().isDisplayed();
		softAssert.assertTrue(isLoginButtonDisplayed, "Login Button is not displayed.");

		// Check the visibility of the Forgot Password link
		boolean isForgotlinkTestDisplayed = LoginPom.getForgotlinkTest().isDisplayed();
		softAssert.assertTrue(isForgotlinkTestDisplayed, "Forgot Password link is not displayed.");

		// Check the visibility of the Sign Up link
		boolean isSignupLinktextDisplayed = LoginPom.getSigupLinktext().isDisplayed();
		softAssert.assertTrue(isSignupLinktextDisplayed, "Sign Up link is not displayed.");

		softAssert.assertAll();
		Reporter.log("All elements in log in page  displayed.", true);
	}
	
	@DataProvider(name = "validLoginData")
	public Object[][] validLoginDataProvider() throws Throwable {
		return new Object[][] { { "kmmanoj831456@gmail.com", "Pwd4so!l" } };
	}

	@Test(dataProvider = "validLoginData", priority = 2)
	public void testValidLogin(String validEmail, String validPassword) {
		// Initialize the Page Object for the Login page
		LoginPagePom loginPage = new LoginPagePom(driver);

		// Initialize SoftAssert for multiple assertions
		SoftAssert softAssert = new SoftAssert();

		// Enter email
		loginPage.getEmailTextField().sendKeys(validEmail);
		// Enter password
		loginPage.getPasswordTextField().sendKeys(validPassword);
		// "Password input did not match the expected value.");
		// Click the 'Log In' button
		loginPage.getLoginButton().click();

		wait.until(ExpectedConditions.urlToBe("https://app.germanyiscalling.com/cv/upload/"));
		// Verify the user is redirected to the correct URL
		String expectedUrl = "https://app.germanyiscalling.com/cv/upload/";
		String actualUrl = driver.getCurrentUrl();
		softAssert.assertEquals(actualUrl, expectedUrl, "URL does not match after login.");
		// Verify the page title after successful login
		String expectedTitle = "Upload your CV | Germany Is Calling";
		String actualTitle = driver.getTitle();
		softAssert.assertEquals(actualTitle, expectedTitle, "Page title does not match after login.");
		// Assert all conditions
		softAssert.assertAll();
	   driver.manage().addCookie(new Cookie("sessionid", ""));
	   driver.navigate().refresh();
	}
	

	@DataProvider(name = "InvaidTestData")
	public Object[][] InvaidTestData1(){
		 Object[][] a= {{"Invalid Email and Password","kmmanoj123@gmail.com","123345"},{"VaildEmail and invalid password","kmmanoj696@gmail.com","Abcddd"},{"Invalid EmailID and validPassword","Kma@gmail.com","Pwd4so!l"}};
	 return a;
	}

	@Test(dataProvider = "InvaidTestData",priority = 3)
	public void TestLoginPageWithInvalidData(String Scenario,String email,String password) throws Throwable {
		LoginPagePom loginPage = new LoginPagePom(driver);
         Reporter.log(Scenario);
		// Initialize SoftAssert
		SoftAssert softAssert = new SoftAssert();

		// Enter email and password based on the scenario
		loginPage.getEmailTextField().sendKeys(email);
		loginPage.getPasswordTextField().sendKeys(password);

		// Click the 'Log In' button
		loginPage.getLoginButton().click();
        Thread.sleep(Duration.ofSeconds(5));
		WebElement Error = driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[3]"));
		
		softAssert.assertTrue(Error.isDisplayed(), Error.getText()+" Error message is not displayed.");
		String expectedError="Please enter a correct username and password. Note that both fields may be case-sensitive.";
		softAssert.assertEquals(Error.getText(), expectedError, "Email error message text is incorrect.");

		// Assert all conditions
		softAssert.assertAll();

	}
	@DataProvider(name = "loginTestData")
	public Object[][] provideLoginTestData() {
		return new Object[][] {
				// Scenario, Email, Password, Expected Email Error, Expected Password Error
				{ "WithoutEmailAndPassword", "", "",
						"Email: This field is required.\n" + "Password: This field is required." },
				{ "WithoutPassword", "validuser@example.com", "", "Password: This field is required." },
				{ "WithoutEmail", "", "validpassword123", "Email: This field is required." } };
	}

	@Test(dataProvider = "loginTestData",priority = 4)
	public void Test_login_With_EmptyTextfields (String scenario, String email, String password, String expectedError) throws InterruptedException {
		LoginPagePom loginPage = new LoginPagePom(driver);

		// Initialize SoftAssert
		SoftAssert softAssert = new SoftAssert();

		// Enter email and password based on the scenario
		loginPage.getEmailTextField().sendKeys(email);
		loginPage.getPasswordTextField().sendKeys(password);

		// Click the 'Log In' button
		loginPage.getLoginButton().click();
        Thread.sleep(Duration.ofSeconds(3));
		WebElement Error = driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/form/div[3]"));
		
		softAssert.assertTrue(Error.isDisplayed(), Error.getText()+" Error message is not displayed.");
		softAssert.assertEquals(Error.getText(), expectedError, "Email error message text is incorrect.");

		// Assert all conditions
		softAssert.assertAll();
	}
	

}
