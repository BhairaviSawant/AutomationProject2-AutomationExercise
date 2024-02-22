package Testcases;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.CartPageElements;
import pageElementsFile.LoginUser;
import pageElementsFile.ProductPage;
import pageElementsFile.RegisterUser;

public class TC20_Search_Products_and_Verify_Cart_After_Login {



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

	public void SearchProduct() throws InterruptedException
	{

		ProductPage productpage = new ProductPage(driver);
		LoginUser LogUserObj = new LoginUser(driver);
		RegisterUser RegUserObj = new RegisterUser(driver);
		CartPageElements CartPageObj =new CartPageElements(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)", "");
		//	3. Click on 'Products' button
		productpage.ClickProduct();

		//	4. Verify user is navigated to ALL PRODUCTS page successfully
		productpage.VerifyProductpage();
		//	5. Enter product name in search input and click search button
		productpage.SearchProduct("top");

		//	6. Verify 'SEARCHED PRODUCTS' is visible
		productpage.VerifySearchProductText();
		//	7. Verify all the products related to search are visible
		productpage.GetProductList();
		//	8. Add those products to cart
		productpage.addAllProductsToCart();
		//	9. Click 'Cart' button and verify that products are visible in cart
		productpage.clickViewCartButton();


		//	10. Click 'Signup / Login' button and submit login details
		RegUserObj.Signup();
		LogUserObj.Login("Testuser1@gmail.com", "123456");
		//	11. Again, go to Cart page
		productpage.clickViewCartButton();
		//	12. Verify that those products are visible in cart after login as well
		int numberOfItems = productpage.getNumberOfItemsInCart();

		 for (int i = 0; i < numberOfItems; i++) {
	            String price = productpage.getProductPrice(i);
	            String quantity = productpage.getProductQuantity(i);
	            String totalPrice = productpage.getProductTotalPrice(i);

	            System.out.println("Product " + (i + 1) + ":");
	            System.out.println("Price: " + price);
	            System.out.println("Quantity: " + quantity);
	            System.out.println("Total Price: " + totalPrice);
	            System.out.println();
	        }
		}
	@AfterTest
	void close()
	{
		driver.close();
		driver.quit();
	}
}
