package Testcases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import pageElementsFile.ProductPage;
import pageElementsFile.RegisterUser;

public class TC12_Add_Products_in_Cart {

	WebDriver driver;
	//	1. Launch browser
	//	2. Navigate to url 'http://automationexercise.com'
	@BeforeTest
	void initialize() throws InterruptedException {

		ChromeOptions opt = new ChromeOptions();
		opt.addExtensions( new File ("./Extensions/AddBlockerPlusextension.crx"));

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
	}


	@Test
	void Addproducttocart() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);
		ProductPage PP = new ProductPage(driver);

		//	3. Verify that home page is visible successfully
		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);

		//	4. Click 'Products' button
		PP.ClickProduct();
		//	5. Hover over first product and click 'Add to cart'
		PP.FirstProductAddToCart();
		//	6. Click 'Continue Shopping' button
		PP.clickContinueShopBTN();
		//	7. Hover over second product and click 'Add to cart'
		PP.SecondProductAddToCart();
		//	8. Click 'View Cart' button
		PP.clickViewCartButton();

		int numberOfItems = PP.getNumberOfItemsInCart();

        // 9.Verify 2 products are added to Cart
        if (numberOfItems == 2) {
            System.out.println("Step 9: 2 products are added to Cart");
        } else {
            System.out.println("Step 9: 2 products are not added to Cart");
        }

        //10.  Verify prices, quantity, and total price for each product
        for (int i = 0; i < numberOfItems; i++) {
            String price = PP.getProductPrice(i);
            String quantity = PP.getProductQuantity(i);
            String totalPrice = PP.getProductTotalPrice(i);

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
	}}
