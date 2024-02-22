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
import pageElementsFile.ProductPage;
import pageElementsFile.RegisterUser;

public class TC17_Remove_Products_From_Cart {


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
	void RemoveProductfromCart() throws InterruptedException
	{

		RegisterUser Obj = new RegisterUser(driver);
		ProductPage ProdPage = new ProductPage(driver);
		CartPageElements CartPage = new CartPageElements(driver);

		//	3. Verify that home page is visible successfully

		String CurrentTitle = driver.getTitle();
		Obj.verifyHome(CurrentTitle);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)", "");
		//ProdPage.SecondProductAddToCart();
		//	4. Add products to cart
		//ProdPage.FirstProductAddToCart();

		ProdPage.AddFirstProducttoCart();

		//	5. Click 'Cart' button
		ProdPage.ClickViewCartText();
		//	6. Verify that cart page is displayed
		CartPage.VerifyShoppingCartpage();

		//	7. Click 'X' button corresponding to particular product
		CartPage.ClickonDeletebtn();
		//	8. Verify that product is removed from the cart
		CartPage.VerifyCartEmptyText();

	}
	
	@AfterTest
	void close()
	{
		driver.close();
	}
}