package pageElementsFile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ContactUS {

	private WebDriver driver;

	@FindBy(xpath ="//a[@href='/contact_us']")
	WebElement ContactusBtn;

	@FindBy(xpath ="//h2[text()='Get In Touch']")
	WebElement ContactUsText;

	@FindBy(name ="email")
	WebElement Email;

	@FindBy(name ="name")
	WebElement Name;

	@FindBy(name ="subject")
	WebElement Subject;

	@FindBy(name ="message")
	WebElement Message;

	@FindBy(name ="upload_file")
	WebElement Fileupload;

	@FindBy(name ="submit")
	WebElement SubmitBtn;

	@FindBy(xpath ="//div[text()= 'Success! Your details have been submitted successfully.']")
	WebElement SuccessMessageText;


	@FindBy(xpath ="//a[@href='/']")
	WebElement HomeBtn;

	public ContactUS(WebDriver driver){
		PageFactory.initElements(driver,this); // test Object
	}

	public void Contactpage()
	{
		ContactusBtn.click();
	}

	public void VerifyContactText() throws InterruptedException
	{
		String ExpText = "GET IN TOUCH";
		System.out.println("Text visible is :"+ContactUsText.getText());

		Assert.assertEquals(ContactUsText.getText(), ExpText, "Text Didnt match");
	}

	public void FillContactUs(String name, String emailid, String Sub , String Msg ) throws InterruptedException
	{
		Name.sendKeys(name);
		Email.sendKeys(emailid);
		Subject.sendKeys(Sub);
		Message.sendKeys(Msg);
	}

	public void uploadfile()

	{
		Fileupload.sendKeys("C:\\Users\\Admin\\Desktop\\unzipFileAutomate\\Test Cases- Insurance.xlsx");
	}

	public void Submit()
	{
		SubmitBtn.click();
	}

	public void VerifySuccessText() throws InterruptedException
	{
		String ExpText = "Success! Your details have been submitted successfully.";
		System.out.println("Text visible is :"+SuccessMessageText.getText());

		Assert.assertEquals(SuccessMessageText.getText(), ExpText, "Text Didnt match");
		HomeBtn.click();
	}


}
