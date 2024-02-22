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

public class TC21_Add_Review_on_Product {


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
	void AddReview() throws InterruptedException
	{
		RegisterUser RegisterUserobj = new RegisterUser(driver);
		ProductPage productpageObj= new ProductPage(driver);

		//	3. Verify that home page is visible successfully
		String ActTitle = driver.getTitle();
		RegisterUserobj.verifyHome(ActTitle);

		//	3. Click on 'Products' button
		productpageObj.ClickProduct();
		//	4. Verify user is navigated to ALL PRODUCTS page successfully
		productpageObj.VerifyProductpage();
		//	5. Click on 'View Product' button
		productpageObj.viewProduct();
		//	6. Verify 'Write Your Review' is visible
		productpageObj.VerifyWriteYourReviewText();
		//	7. Enter name, email and review
		//	8. Click 'Submit' button
		productpageObj.EnterReviewDetails("ReviewName", "Reviewname@gmail.com", "ReviewText message");
		//	9. Verify success message 'Thank you for your review.'
		productpageObj.VerifyThankYouMessage();

	}

	@AfterTest
	void close()
	{
		driver.close();
	}

}
