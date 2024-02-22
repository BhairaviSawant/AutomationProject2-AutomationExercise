package Testcases;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.CartPageElements;
import pageElementsFile.LoginUser;
import pageElementsFile.ProductPage;
import pageElementsFile.RegisterUser;

public class TC16_Place_order_Login_before_Checkout {


	//	1. Launch browser
	//	2. Navigate to url 'http://automationexercise.com'
	WebDriver driver;

	@BeforeTest
	void Initilaize()
	{
		//1. Launch browser
		ChromeOptions opt = new ChromeOptions();
		opt.addExtensions( new File ("./Extensions/AddBlockerPlusextension.crx"));
		//2. Navigate to url 'http://automationexercise.com'
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.get("http://automationexercise.com");
	}

	@Test
	void PlaceOrderLoginonCheckout() throws InterruptedException
	{

		RegisterUser Obj = new RegisterUser(driver);
		ProductPage Op = new ProductPage(driver);
		CartPageElements CP = new CartPageElements(driver);
		LoginUser LogUserObj = new LoginUser(driver);

		//3. Verify that home page is visible successfully
		String CurrentTitle = driver.getTitle();
		Obj.verifyHome(CurrentTitle);


		//	4. Click 'Signup / Login' button
		Obj.Signup();
		//	5. Fill email, password and click 'Login' button
		LogUserObj.Login("Testcase16@gmail.com", "123456");
		//	6. Verify 'Logged in as username' at top
		Obj.VerifyuserId();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)", "");

		//	7. Add products to cart
		Op.AddFirstProducttoCart();

		//	8. Click 'Cart' button
		Op.clickViewCartButton();
		//	9. Verify that cart page is displayed
		CP.VerifyShoppingCartpage();
		//	10. Click Proceed To Checkout
		CP.ClickProceedCheckOutBTN();
		//	11. Verify Address Details and Review Your Order
		CP.VerifyAddressDetails();
		//	12. Enter description in comment text area and click 'Place Order'
		CP.EnterDescriptionTextbox("Testcase16");
		CP.ClickPlaceOrderBTN();
		//	13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		CP.EnterCardDetails("Test16", "78787999", "090", "07", "2028");
		//	14. Click 'Pay and Confirm Order' button
		CP.ClickpaymentConfrimOrder();
		//	15. Verify success message 'Your order has been placed successfully!'
		CP.VerifySuccessmessageOrderConfrim();
		//	16. Click 'Delete Account' button
		//	17. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		Obj.Verifydelete();


	}
	
	@AfterTest
	void close()
	{
		driver.close();
	}
}
