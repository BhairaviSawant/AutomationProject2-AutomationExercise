package Testcases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import pageElementsFile.HomePageElements;
import pageElementsFile.RegisterUser;

public class TC10_Verify_Subscription_in_home_page {


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
	void Subscription()
	{
		RegisterUser ob = new RegisterUser(driver);
		HomePageElements HomePage = new HomePageElements(driver);
		//	3. Verify that home page is visible successfully

		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);

		//	4. Scroll down to footer
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");

		//	5. Verify text 'SUBSCRIPTION'
		HomePage.VerifySubscription();
		//	6. Enter email address in input and click arrow button
		HomePage.EnterSubscriptionEmail("TestEmail@yahhoo.com");
		//	7. Verify success message 'You have been successfully subscribed!' is visible
		HomePage.VerifySuccessText();

	}
	
	@AfterTest
	void close()
	{
		driver.close();
	}}
