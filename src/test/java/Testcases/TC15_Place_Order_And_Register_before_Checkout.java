package Testcases;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.CartPageElements;
import pageElementsFile.ProductPage;
import pageElementsFile.RegisterUser;

public class TC15_Place_Order_And_Register_before_Checkout {

	//	1. Launch browser

	WebDriver driver;

	@BeforeTest
	void Initilaize()
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


	//	3. Verify that home page is visible successfully

	@Test
	void PlaceOrder() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);
		ProductPage Op = new ProductPage(driver);
		CartPageElements CP = new CartPageElements(driver);



		//	4. Click 'Signup / Login' button
		ob.Signup();
		//	5. Fill all details in Signup and create account
		ob.EnterDetails("Namefifteen", "Fifteen@gmail.com");
		ob.EnterAccountInfo("FnameNew", "Fname@gmail.com", "123456");

		ob.CheckboxSelect();
		ob.AddressDetails("Namefifteen", "Lname", "PlanIt", "Street1", "Street2", "Maharashtra", "Mumbai", "878787", "9087654324");
		//	6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		ob.verifyHeadingAccountCreated();
		//	7. Verify ' Logged in as username' at top
		ob.VerifyuserId();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)", "");

		//	8. Add products to cart
		Op.AddFirstProducttoCart();
		//	9. Click 'Cart' button
		Op.clickViewCartButton();

		//	10. Verify that cart page is displayed
		CP.VerifyShoppingCartpage();
		//	11. Click Proceed To Checkout
		CP.ClickProceedCheckOutBTN();
		//	12. Verify Address Details and Review Your Order
		CP.VerifyAddressDetails();
		//	13. Enter description in comment text area and click 'Place Order'
		CP.EnterDescriptionTextbox("Test case number 15");
		CP.ClickPlaceOrderBTN();
		//	14. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		CP.EnterCardDetails("TestName", "67673434", "456", "March", "2026");
		//	15. Click 'Pay and Confirm Order' button
		CP.ClickpaymentConfrimOrder();
		//	16. Verify success message 'Your order has been placed successfully!'
		CP.VerifySuccessmessageOrderConfrim();
		//	17. Click 'Delete Account' button

		//	18. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		ob.Verifydelete();

	}
	
	@AfterTest
	void close()
	{
		driver.close();
		driver.quit();
	}
}