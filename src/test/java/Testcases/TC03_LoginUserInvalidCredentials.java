package Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.LoginUser;
import pageElementsFile.RegisterUser;

public class TC03_LoginUserInvalidCredentials {


	//	1. Launch browser
	//	2. Navigate to url 'http://automationexercise.com'

	WebDriver driver;

	@BeforeTest
	void initialize() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
	}
	//	3. Verify that home page is visible successfully

	@Test
	void InValidUser() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);
		LoginUser LogUserObj = new LoginUser(driver);

		//	4. Click on 'Signup / Login' button
		ob.Signup();

		//	5. Verify 'Login to your account' is visible
		LogUserObj.verifyHeading();
		//	6. Enter incorrect email address and password
		//	7. Click 'login' button
		LogUserObj.Login("Testuser1@gmail.com", "12345689");

		//	8. Verify error 'Your email or password is incorrect!' is visible
		LogUserObj.Validationmessage();

	}

	@AfterTest
	void close()
	{
		driver.close();
	}
}
