package Testcases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.ProductPage;
import pageElementsFile.RegisterUser;

public class TC08_Verify_All_Product_and_Details {

	//	1. Launch browser
	//	2. Navigate to url 'http://automationexercise.com'
	WebDriver driver;

	@BeforeTest
	void initialize() throws InterruptedException {
		ChromeOptions opt = new ChromeOptions();
		opt.addExtensions( new File ("./Extensions/AddBlockerPlusextension.crx"));
		opt.addArguments("--disable-notifications");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
	}
	//	3. Verify that home page is visible successfully

	@Test
	void ViewAllProductdetails() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);
		ProductPage PP = new ProductPage(driver);

		// 3. Verify that home page is visible successfully
		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);
		//	4. Click on 'Products' button
		PP.ClickProduct();
		//	5. Verify user is navigated to ALL PRODUCTS page successfully
		PP.VerifyProductpage();
		//	6. The products list is visible
		PP.GetProductList();
		//	7. Click on 'View Product' of first product
		//	8. User is landed to product detail page
		//	9. Verify that detail detail is visible: product name, category, price, availability, condition, brand
		PP.Firstproduct();
	}
	@AfterTest
	void close()
	{
		driver.close();
	}

}
