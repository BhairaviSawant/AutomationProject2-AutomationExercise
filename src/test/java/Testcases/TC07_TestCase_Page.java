package Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageElementsFile.RegisterUser;
import pageElementsFile.TestcasePage;

public class TC07_TestCase_Page {

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
	void TestcasesView() throws InterruptedException
	{
		RegisterUser ob = new RegisterUser(driver);
		TestcasePage TC= new TestcasePage(driver);
		//  3. Verify that home page is visible successfully
		String ActTitle = driver.getTitle();
		ob.verifyHome(ActTitle);
		//	4. Click on 'Test Cases' button
		//	5. Verify user is navigated to test cases page successfully
		TC.VerifyTestcasePage();

	}

	@AfterTest
	void close()
	{
		driver.close();
	}
}
