package Testcases;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.RegisterUser;



public class TC01_RegisterUser {

	WebDriver driver;

	@BeforeTest
	void initialize() throws InterruptedException
	{
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
	void RegisterUserS1() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);


		// 3. Verify that home page is visible successfully

		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);

		//4. Click on 'Signup / Login' button
		ob.Signup();

		// 5. Verify 'New User Signup!' is visible
		ob.verifyHeading();

		//6. Enter name and email address
		//7. Click 'Signup' button
		ob.EnterDetails("TestCaseOne", "TestCase0NE@gmail.com");

		//8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
		ob.verifyHeadingAccountInfo();

		//9. Fill details: Title, Name, Email, Password, Date of birth
		ob.EnterAccountInfo("Fname", "Fname@gmail.com", "123456");

		//10. Select checkbox 'Sign up for our newsletter!'
		//11. Select checkbox 'Receive special offers from our partners!'
		ob.CheckboxSelect();
		//12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
		//13. Click 'Create Account button'

		ob.AddressDetails("TestCase", "First", "PlanIt", "Street1", "Street2", "Maharashtra", "Mumbai", "878787", "9087654324");
		//14. Verify that 'ACCOUNT CREATED!' is visible and Click 'Continue' button
		ob.verifyHeadingAccountCreated();
		//16. Verify that 'Logged in as username' is visible
		ob.VerifyuserId();
		//17. Click 'Delete Account' button
		//18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
		ob.Verifydelete();
	}
	
	@AfterTest
	void close()
	{
		driver.close();
		driver.quit();
	}

}
