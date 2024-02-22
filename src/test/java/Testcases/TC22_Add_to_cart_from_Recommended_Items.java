package Testcases;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.ProductPage;
import pageElementsFile.RecommendedItems;

public class TC22_Add_to_cart_from_Recommended_Items {

	WebDriver driver;
	//	1. Launch browser
	//	2. Navigate to url 'http://automationexercise.com'
	@BeforeTest
	void initialize() throws InterruptedException {

		ChromeOptions opt = new ChromeOptions();
		opt.addExtensions( new File ("./Extensions/AddBlockerPlusextension.crx"));

		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
	}

	@Test
	void ADDRecommendedItems() throws InterruptedException
	{
		RecommendedItems RecommendeditemObj = new RecommendedItems(driver);
		ProductPage productpage = new ProductPage(driver);


		//	3. Scroll to bottom of page
		//	4. Scroll down to footer

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", RecommendeditemObj.ScrolltoRecommendedtext);
		//js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");


		//  Verify 'RECOMMENDED ITEMS' are visible
		boolean areRecommendedItemsVisible = RecommendeditemObj.isRecommendedItemsVisible();
		System.out.println("'RECOMMENDED ITEMS' section is visible: " + areRecommendedItemsVisible);

		// Click on 'Add To Cart' on visible Recommended product
		if (areRecommendedItemsVisible) {
			RecommendeditemObj.GetRecommendedProductList();
			System.out.println("Clicked on 'Add To Cart' on visible Recommended product.");
		} else {
			System.out.println("No recommended items are visible.");
		}

		RecommendeditemObj.addAllRecommendedProductsToCart();

		//	6. Click on 'View Cart' button
		productpage.clickViewCartButton();


		//	7. Verify that product is displayed in cart page

		int numberOfItems = productpage.getNumberOfItemsInCart();

		for (int i = 0; i < numberOfItems; i++) {
			String price = productpage.getProductPrice(i);
			String quantity = productpage.getProductQuantity(i);
			String totalPrice = productpage.getProductTotalPrice(i);
			System.out.println("Product " + (i + 1) + ":");
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
