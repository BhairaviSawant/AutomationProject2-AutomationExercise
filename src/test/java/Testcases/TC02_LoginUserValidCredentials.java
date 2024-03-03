package Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.LoginUser;
import pageElementsFile.RegisterUser;

public class TC02_LoginUserValidCredentials {

	//	1. Launch browser

	WebDriver driver;

	@BeforeTest
	void initialize() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
	}

	//	2. Navigate to url 'http://automationexercise.com'
	//	3. Verify that home page is visible successfully
	@Test
	void ValidUser() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);
		LoginUser LogUserObj = new LoginUser(driver);

		// 3. Verify that home page is visible successfully

		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);

		//4. Click on 'Signup / Login' button
		ob.Signup();
		//	5. Verify 'Login to your account' is visible
		LogUserObj.verifyHeading();
		//	6. Enter correct email address and password
		//	7. Click 'login' button
		LogUserObj.Login("Testuser1@gmail.com", "123456");
		//	8. Verify that 'Logged in as username' is visible
		ob.VerifyuserId();
		//	9. Click 'Delete Account' button
		//ob.Verifydelete();

		//	10. Verify that 'ACCOUNT DELETED!' is visible
	}


	@AfterTest
	void close()
	{
		driver.close();
	}

}
