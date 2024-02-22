package Testcases;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.HomePageElements;
import pageElementsFile.RegisterUser;

public class TC26_Verify_Scroll_Up_Down_functionality_WithoutArrow {


	WebDriver driver;

	@BeforeTest
	void Initilaize()
	{
		//1. Launch browser
		ChromeOptions opt = new ChromeOptions();
		opt.addExtensions( new File ("./Extensions/AddBlockerPlusextension.crx"));
		opt.addArguments("--disable-notifications");

		// Set user preferences to disable autofill profile
		opt.setExperimentalOption("prefs",
				new HashMap<String, Object>() {{
					put("autofill.profile_enabled", false);
				}});
		opt.addArguments("--disable-save-password-bubble");
		//2. Navigate to url 'http://automationexercise.com'
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.get("http://automationexercise.com");
	}

	@Test
	public void ScrollUpwithWithoutArrow() throws InterruptedException
	{
		RegisterUser Obj = new RegisterUser(driver);

		HomePageElements HomePage = new HomePageElements(driver);

		//		3. Verify that home page is visible successfully

		String ActTitle = driver.getTitle();
		Obj.verifyHome(ActTitle);



		//	4. Scroll down page to bottom
		//		4. Scroll down to footer
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		//	5. Verify 'SUBSCRIPTION' is visible
		HomePage.VerifySubscription();

	        // 6. Scroll to the top of the webpage
	        js.executeScript("window.scrollTo(0, 0)");
	        //7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
		HomePage.VerifyHeaderTextOnTOP();
	}

	@AfterTest
	void Close()
	{
		driver.quit();
		}

}
