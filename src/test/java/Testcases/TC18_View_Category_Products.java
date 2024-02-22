package Testcases;

import org.testng.*;
import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.*;

import pageElementsFile.HomePageElements;

public class TC18_View_Category_Products {

	WebDriver driver;

	@BeforeTest
	void Initilaize()
	{
		//1. Launch browser
		ChromeOptions opt = new ChromeOptions();
		opt.addExtensions( new File ("./Extensions/AddBlockerPlusextension.crx"));
		//2. Navigate to url 'http://automationexercise.com'
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationexercise.com");
	}

	@Test
	public void ViewbyCategory()
	{

		HomePageElements homePage = new HomePageElements(driver);

		//	3. Verify that categories are visible on left side bar
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,550)", "");
		

        Assert.assertTrue(homePage.VerifyCategoriesVisible(), "Categories are not visible on the left sidebar");
		//	4. Click on 'Women' category
        homePage.clickWomenCategory();
		//	5. Click on any category link under 'Women' category, for example: Dress
        homePage.clickWomenDress();
		//	6. Verify that category page is displayed and confirm text 'WOMEN - TOPS PRODUCTS'
        homePage.VerifyWomensCategoryHeadingText();
		//	7. On left side bar, click on any sub-category link of 'Men' category
        homePage.clickMenCategory();
        homePage.clickMensSubOption();
		//	8. Verify that user is navigated to that category page
        homePage.VerifyMensCategoryHeadingText();

	}

	
	@AfterTest
    public void close() {
            driver.quit();
        }
}
