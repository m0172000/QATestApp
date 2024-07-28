package TestScripts;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonUtlis.BaseClass;
import PomRepository.LandingPagePom;
import PomRepository.LoginPagePom;
import PomRepository.RegistrationScreenPom;

public class landingScreen extends BaseClass{
	
	@Test(priority = 1)
    public void verifyLandingPageElements() {
        LandingPagePom landingPage = new LandingPagePom(driver);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(landingPage.getPageTitle().isDisplayed(), "Page title is not displayed.");
        softAssert.assertTrue(landingPage.getPageContentText().isDisplayed(), "Page content is not displayed.");

        softAssert.assertAll();
        Reporter.log("The landing page verifyed, The title and content are displaying ");
    }

    @Test(priority = 2)
    public void verifyLandingpageContentAfterRegistration() {
        LandingPagePom landingPage = new LandingPagePom(driver);
        SoftAssert softAssert = new SoftAssert();

        String expectedContent = "My name is Manu KM. You can reach me by calling me on 6362870967. Also, mail me at kmmanu831813@gmail.com";
        String actualContent = landingPage.getPageContentText().getText();

        softAssert.assertEquals(actualContent, expectedContent, "The page content text does not match the expected content.");

        softAssert.assertAll();
        Reporter.log("The Landing page content is displaying as expected after register sucessfully");
    }

    @BeforeClass
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
		
		RegistrationScreenPom registrationScreen1 = new RegistrationScreenPom(driver);
		SoftAssert softAssert = new SoftAssert();


		registrationScreen1.getFullnametextfield().sendKeys("Manu KM");
		registrationScreen1.getEmailtextfield().sendKeys("kmmanu831813@gmail.com");
		registrationScreen1.getPhoneNUmbertextfield().sendKeys("6362870967");
			registrationScreen1.getSubscribeCheckBox().click();
		registrationScreen1.getSubmitButton().click();
	}

}
