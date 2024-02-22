package Testcases;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.*;

import pageElementsFile.CartPageElements;
import pageElementsFile.ProductPage;
import pageElementsFile.RegisterUser;

public class TC14_Place_Order_With_Registration {
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

	@Test
	public void PlaceOrderWithRegistration() throws InterruptedException
	{
		//	3. Verify that home page is visible successfully
		RegisterUser Obj = new RegisterUser(driver);
		ProductPage Op = new ProductPage(driver);
		CartPageElements CP = new CartPageElements(driver);

		String CurrentTitle = driver.getTitle();
		Obj.verifyHome(CurrentTitle);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)", "");

		//	4. Add products to cart

		//Op.FirstProductAddToCart();
		Op.AddFirstProducttoCart();
		Op.clickContinueShopBTN();
		//	5. Click 'Cart' button
		CP.ClickProduct();

		//	6. Verify that cart page is displayed
		CP.VerifyShoppingCartpage();
		//	7. Click Proceed To Checkout
		CP.ClickProceedCheckOutBTN();
		//	8. Click 'Register / Login' button
		CP.ClickonRegister();
		//	9. Fill all details in Signup and create account
		Obj.EnterDetails("TestCase14", "NewAccount@gmail.com");
		Obj.EnterAccountInfo("TestCase14", "Fname@gmail.com", "123456");

		Obj.CheckboxSelect();
		Obj.AddressDetails("Fname", "Lname", "PlanIt", "Street1", "Street2", "Maharashtra", "Mumbai", "878787", "9087654324");
		//	10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		Obj.verifyHeadingAccountCreated();
		//	11. Verify ' Logged in as username' at top
		Obj.VerifyuserId();
		//	12.Click 'Cart' button
		Op.clickViewCartButton();
		//	13. Click 'Proceed To Checkout' button
		CP.ClickProceedCheckOutBTN();
		//	14. Verify Address Details and Review Your Order
		CP.VerifyAddressDetails();

		int numberOfItems = Op.getNumberOfItemsInCart();

		for (int i = 0; i < numberOfItems; i++)
		{
			String price = Op.getProductPrice(i);
			String quantity = Op.getProductQuantity(i);
			String totalPrice = Op.getProductTotalPrice(i);

			System.out.println("Product " + (i + 1) + ":");
			System.out.println("Price: " + price);
			System.out.println("Quantity: " + quantity);
			System.out.println("Total Price: " + totalPrice);
			System.out.println();
		}
		//	15. Enter description in comment text area and click 'Place Order'
		CP.EnterDescriptionTextbox("TEXT in message.");
		CP.ClickPlaceOrderBTN();

		//	16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		CP.EnterCardDetails("TestCaseFourteen", "67673434", "456", "March", "2026");

		//	17. Click 'Pay and Confirm Order' button
		CP.ClickpaymentConfrimOrder();
		//	18. Verify success message 'Your order has been placed successfully!'
		CP.VerifyOrderSuccessmessage();
		//	19. Click 'Delete Account' button

		//	20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		Obj.Verifydelete();

	}
	
	@AfterTest
	void close()
	{
		driver.close();
		driver.quit();
	}
	}
