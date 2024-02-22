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

public class TC23_Verify_Address_Details_Checkout_Page {

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
	public void AddressOnCheckoutPage() throws InterruptedException
	{
		RegisterUser Obj = new RegisterUser(driver);
		ProductPage ProductPageObj = new ProductPage(driver);
		CartPageElements CartPage = new CartPageElements(driver);
		//		3. Verify that home page is visible successfully

		String ActTitle = driver.getTitle();
		Obj.verifyHome(ActTitle);
		//		4. Click 'Signup / Login' button
		Obj.Signup();

		//		5. Fill all details in Signup and create account
		Obj.EnterDetails("Testcase Twenty Three", "TestCase23@gmail.com");
		Obj.EnterAccountInfo("Testcase Twenty Three", "TestCase23@gmail.com", "123456");
		Obj.AddressDetails("Fname", "Lname", "PlanIt", "Street1", "Street2", "Maharashtra", "Mumbai", "878787", "9087654324");
		//		6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		Obj.verifyHeadingAccountCreated();
		//		7. Verify ' Logged in as username' at top
		Obj.VerifyuserId();
		//		8. Add products to cart
		ProductPageObj.AddFirstProducttoCart();
		ProductPageObj.ClickViewCartText();
		//		9. Click 'Cart' button
		ProductPageObj.clickViewCartButton();
		//		10. Verify that cart page is displayed
		CartPage.VerifyShoppingCartpage();
		//		11. Click Proceed To Checkout
		CartPage.ClickProceedCheckOutBTN();
		//		12. Verify that the delivery address is same address filled at the time registration of account
		// Verify delivery address
        String expectedDeliveryAddress = "Street1, Street2, Maharashtra, Mumbai, 878787";
        String actualDeliveryAddress = CartPage.getDeliveryAddress();
        System.out.println(actualDeliveryAddress);
        if (actualDeliveryAddress.contentEquals(expectedDeliveryAddress)) {
            System.out.println("Delivery address is same as provided address.");
        } else {
            System.out.println("Delivery address is different from provided address.");
        }
		//		13. Verify that the billing address is same address filled at the time registration of account
        String expectedBillingAddress = "Street1, Street2, Maharashtra, Mumbai, 878787";
        String actualBillingAddress = CartPage.getBillingAddress();
        System.out.println(actualBillingAddress);

        if (actualBillingAddress.contentEquals(expectedBillingAddress)) {
            System.out.println("Billing address is same as provided address.");
        } else {
            System.out.println("Billing address is different from provided address.");
        }


		//		15. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		Obj.Verifydelete();
	}
	@AfterTest
	void close()
	{
		driver.close();
		driver.quit();
	}
}
