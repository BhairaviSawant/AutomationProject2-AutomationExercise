package Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import pageElementsFile.LoginUser;
import pageElementsFile.RegisterUser;

public class TC04_LogoutUser {

	//	1. Launch browser
	//	2. Navigate to url 'http://automationexercise.com'
	WebDriver driver;

	@BeforeTest
	void initialize() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
	}

	@Test
	void Logoutuser() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);
		LoginUser LogUserObj = new LoginUser(driver);

		// 3. Verify that home page is visible successfully

		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);
		//	4. Click on 'Signup / Login' button
		ob.Signup();

		//	5. Verify 'Login to your account' is visible
		LogUserObj.verifyHeading();

		//	6. Enter correct email address and password
		//	7. Click 'login' button
		LogUserObj.Login("Fname1@gmail.com", "123456");

		//	8. Verify that 'Logged in as username' is visible
		ob.VerifyuserId();
		//	9. Click 'Logout' button
		LogUserObj.Logout();

		//	10. Verify that user is navigated to login page
		LogUserObj.verifyHeading();

	}
	@AfterTest
	void close()
	{
		driver.close();
	}
}