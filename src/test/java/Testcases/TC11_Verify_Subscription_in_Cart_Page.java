package Testcases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.HomePageElements;
import pageElementsFile.RegisterUser;

public class TC11_Verify_Subscription_in_Cart_Page {

	//	1. Launch browser
	//	2. Navigate to url 'http://automationexercise.com'
	WebDriver driver;
	@BeforeTest
	void Initialize()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
	}

	@Test
	public void VerifySubscription()
	{
		//		3. Verify that home page is visible successfully

		RegisterUser Obj = new RegisterUser(driver);
		HomePageElements HP = new HomePageElements(driver);

		String ActTitle = driver.getTitle();
		Obj.verifyHome(ActTitle);

		//	4. Click 'Cart' button
		HP.ClickCart();

		//	5. Scroll down to footer
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		//	6. Verify text 'SUBSCRIPTION'
		HP.VerifySubscription();

		//	7. Enter email address in input and click arrow button
		HP.EnterSubscriptionEmail("TestEmail@yahhoo.com");

		//	8. Verify success message 'You have been successfully subscribed!' is visible
		HP.VerifySuccessText();

	}

	@AfterTest
	void closeWindow()
	{
		driver.close();
	}
}
