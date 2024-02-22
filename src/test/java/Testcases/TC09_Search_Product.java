package Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.ProductPage;
import pageElementsFile.RegisterUser;

public class TC09_Search_Product {


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
	void SearchProduct() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);
		ProductPage PP = new ProductPage(driver);

		//	3. Verify that home page is visible successfully
		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);

		//	4. Click on 'Products' button
		PP.ClickProduct();
		//	5. Verify user is navigated to ALL PRODUCTS page successfully
		PP.VerifyProductpage();

		//	6. Enter product name in search input and click search button
		PP.SearchProduct("top");
		//	7. Verify 'SEARCHED PRODUCTS' is visible
		PP.VerifySearchProductText();
		//	8. Verify all the products related to search are visible
		PP.GetProductList();

	}

	@AfterTest
	void close()
	{
		driver.close();
	}

	}
