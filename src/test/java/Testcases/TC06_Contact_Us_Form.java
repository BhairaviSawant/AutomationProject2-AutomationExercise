package Testcases;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.ContactUS;
import pageElementsFile.RegisterUser;

public class TC06_Contact_Us_Form {

	WebDriver driver;
	//	1. Launch browser
	//	2. Navigate to url 'http://automationexercise.com'
	@BeforeTest
	void initialize() throws InterruptedException {
		//1. Launch browser
		ChromeOptions opt = new ChromeOptions();
		opt.addExtensions( new File ("./Extensions/AddBlockerPlusextension.crx"));
		opt.addArguments("--disable-notifications");

		// Set user preferences to disable autofill profile
		opt.setExperimentalOption("prefs",
				new HashMap<String, Object>() {{
					put("autofill.profile_enabled", false);
				}});
		opt.addArguments("--disable-save-password-bubble");
		//2. Navigate to url 'http://automationexercise.com'
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.get("http://automationexercise.com");
	}

	@Test
	void contactUS() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);
		ContactUS CtObj = new ContactUS(driver);
	//	3. Verify that home page is visible successfully
		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);

	//	4. Click on 'Contact Us' button
		CtObj.Contactpage();
	//	5. Verify 'GET IN TOUCH' is visible
		CtObj.VerifyContactText();
	//	6. Enter name, email, subject and message
		CtObj.FillContactUs("Fname", "Fname@yahoo.com", "Testing Subject", "Textbox testing");
	//	7. Upload file
		CtObj.uploadfile();
	//	8. Click 'Submit' button
		CtObj.Submit();
	//	9. Click OK button
		driver.switchTo().alert().accept();

	//	10. Verify success message 'Success! Your details have been submitted successfully.' is visible
		CtObj.VerifySuccessText();
	//	11. Click 'Home' button and verify that landed to home page successfully

	}
	@AfterTest
	void close()
	{
		driver.close();
		driver.quit();
	}
}
