package Testcases;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.CartPageElements;
import pageElementsFile.ProductPage;
import pageElementsFile.RegisterUser;

public class TC24_Download_Invoice_after_purchase_order {



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
	public void Downloadainvoice() throws InterruptedException
	{
		RegisterUser Obj = new RegisterUser(driver);
		ProductPage ProductPageObj = new ProductPage(driver);
		CartPageElements CartPage = new CartPageElements(driver);
		//		3. Verify that home page is visible successfully

		String ActTitle = driver.getTitle();
		Obj.verifyHome(ActTitle);


		//	4. Add products to cart
		ProductPageObj.AddFirstProducttoCart();
		ProductPageObj.ClickViewCartText();

		//	5. Click 'Cart' button
		//ProductPageObj.clickViewCartButton();

		//	6. Verify that cart page is displayed
		CartPage.VerifyShoppingCartpage();

		//	7. Click Proceed To Checkout

		CartPage.ClickProceedCheckOutBTN();


		//	8. Click 'Register / Login' button
		CartPage.ClickonRegister();

		//	9. Fill all details in Signup and create account

		Obj.EnterDetails("Testcase Twenty Four", "TestCase23@gmail.com");
		Obj.EnterAccountInfo("Testcase Twenty Four", "TestCase23@gmail.com", "123456");
		Obj.AddressDetails("Testcase", "Twenty Four", "PlanIt", "Street1", "Street2", "Maharashtra", "Mumbai", "878787", "9087654324");
		//	10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		Obj.verifyHeadingAccountCreated();

		//	11. Verify ' Logged in as username' at top
		Obj.VerifyuserId();

		//	12.Click 'Cart' button
		ProductPageObj.clickViewCartButton();

		//	13. Click 'Proceed To Checkout' button
		CartPage.ClickProceedCheckOutBTN();

		//	14. Verify Address Details and Review Your Order
		CartPage.VerifyAddressDetails();
		//	15. Enter description in comment text area and click 'Place Order'
		CartPage.EnterDescriptionTextbox("Description - product details");
		CartPage.ClickPlaceOrderBTN();
		//	16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		CartPage.EnterCardDetails("tc24", "787656788", "000", "09", "2029");
		//	17. Click 'Pay and Confirm Order' button
		CartPage.ClickpaymentConfrimOrder();
		//	18. Verify success message 'Your order has been placed successfully!'
		CartPage.VerifyOrderSuccessmessage();
		//	19. Click 'Download Invoice' button and verify invoice is downloaded successfully.
		// Verify if the invoice is downloaded successfully
        boolean isInvoiceDownloaded = CartPage.isInvoiceDownloadedSuccessfully("invoice.txt");
        if (isInvoiceDownloaded) {
            System.out.println("Invoice downloaded successfully.");
        } else {
            System.out.println("Invoice download failed.");
        }
		//	20. Click 'Continue' button
        CartPage.OrderPlacedContinueBtn();
		//	21. Click 'Delete Account' button
		//	22. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        Obj.Verifydelete();


	}
	@AfterTest
	void close()
	{
		driver.close();
	}}
