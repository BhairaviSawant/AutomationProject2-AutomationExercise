package Testcases;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.HomePageElements;
import pageElementsFile.ProductPage;

public class TC19_View_Cart_Brand_Products {



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

	public void ViewbyBrands()
	{

		HomePageElements homePage = new HomePageElements(driver);
		ProductPage productpage = new ProductPage(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)", "");


	//	3. Click on 'Products' button
		productpage.ClickProduct();
	//	4. Verify that Brands are visible on left side bar
        Assert.assertTrue(homePage.VerifyBrandsVisible(), "Brands are not visible on the left sidebar");

	//	5. Click on any brand name
        homePage.clickPoloBrand();
	//	6. Verify that user is navigated to brand page and brand products are displayed
        homePage.VerifyPolobrandName();
	//	7. On left side bar, click on any other brand link
        homePage.clickBIBABrand();
	//	8. Verify that user is navigated to that brand page and can see products
        homePage.VerifyBibabrandName();
}
	
	@AfterTest
    public void tearDown() {
        // Close the browser
            driver.quit();
        }
}
