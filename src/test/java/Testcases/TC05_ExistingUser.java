package Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.RegisterUser;

public class TC05_ExistingUser {

	WebDriver driver;

	//	1. Launch browser
	//	2. Navigate to url 'http://automationexercise.com'



	@BeforeTest
	void initialize() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
	}

	@Test
	void ValidExistingUser() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);
		//	3. Verify that home page is visible successfully
		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);
		//	4. Click on 'Signup / Login' button
		ob.Signup();

		//	5. Verify 'New User Signup!' is visible
		ob.verifyHeading();
		//	6. Enter name and already registered email address
		//  7. Click 'Signup' button
		ob.EnterDetails("TestcaseFive", "Fname1@gmail.com");


		//	8. Verify error 'Email Address already exist!' is visible
		ob.VerifyUserExists();

	}
	
	@AfterTest
	void close()
	{
		driver.close();
	}
}
