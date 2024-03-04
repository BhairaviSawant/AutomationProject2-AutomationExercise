package pageElementsFile;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TestcasePage {

private WebDriver driver;
WebDriverWait wait;

	@FindBy(xpath ="//a[@href='/test_cases']")
	WebElement TestcaseBtn;

	@FindBy(xpath ="//b[text()='Test Cases']")
	WebElement VerifyTCText;

	//b[text()='Test Cases']

	public TestcasePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	}

	public void VerifyTestcasePage()
	{
		TestcaseBtn.click();
		String Exptitle = "TEST CASES";
		wait.until(ExpectedConditions.visibilityOf(VerifyTCText));

		System.out.println("Text visible is :"+VerifyTCText.getText());

		Assert.assertEquals(VerifyTCText.getText(), Exptitle, "Text Didnt match");

	}
}
