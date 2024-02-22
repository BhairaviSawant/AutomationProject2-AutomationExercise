package pageElementsFile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestcasePage {

private WebDriver driver;

	@FindBy(xpath ="//a[@href='/test_cases']")
	WebElement TestcaseBtn;

	@FindBy(xpath ="//b[text()='Test Cases']")
	WebElement VerifyTCText;

	//b[text()='Test Cases']

	public TestcasePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public void VerifyTestcasePage()
	{
		TestcaseBtn.click();
		String Exptitle = "TEST CASES";

		System.out.println("Text visible is :"+VerifyTCText.getText());

		Assert.assertEquals(VerifyTCText.getText(), Exptitle, "Text Didnt match");

	}
}
