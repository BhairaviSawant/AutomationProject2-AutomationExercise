package Testcases;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.ProductPage;
import pageElementsFile.RegisterUser;

public class TC13_Verify_Product_quantity_in_Cart {

	//	1. Launch browser
	//	2. Navigate to url 'http://automationexercise.com'
	WebDriver driver;

	@BeforeTest
	void initialize() throws InterruptedException {
		ChromeOptions opt = new ChromeOptions();
		opt.addExtensions( new File ("./Extensions/AddBlockerPlusextension.crx"));
		opt.addArguments("--disable-notifications");

		// Set user preferences to disable autofill profile
		opt.setExperimentalOption("prefs",
				new HashMap<String, Object>() {{
					put("autofill.profile_enabled", false);
				}});
		opt.addArguments("--disable-save-password-bubble");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
	}
	@Test
	void AddProductCount() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);

		ProductPage PP = new ProductPage(driver);

		//	3. Verify that home page is visible successfully
		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);

		//	4. Click 'View Product' for any product on home page
		PP.viewProduct();
		//	5. Verify product detail is opened
		//	6. Increase quantity to 4
		PP.AddProductQuantity("4");
		//	7. Click 'Add to cart' button
		PP.Addcartbtn();

		//	8. Click 'View Cart' button
		PP.clickViewCartButton();

		//	9. Verify that product is displayed in cart page with exact quantity

		PP.GetProductList();
		PP.getProductQuantity(1);


	}
	@AfterTest
	void close()
	{
		driver.close();
	}
}