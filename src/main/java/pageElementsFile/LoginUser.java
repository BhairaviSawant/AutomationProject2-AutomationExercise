package pageElementsFile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginUser {

	private WebDriver driver;

	@FindBy(xpath ="//h2[text()='Login to your account']")
	WebElement LoginText;
	@FindBy(xpath ="//input[@data-qa ='login-email']")
	WebElement email;
	@FindBy(xpath ="//input[@data-qa ='login-password']")
	WebElement password;
	@FindBy(xpath ="//button[text() ='Login']")
	WebElement loginBTN;
	@FindBy(xpath ="//p[text()='Your email or password is incorrect!']")
	WebElement validationmsgTxt;
	@FindBy(xpath ="//a[@href='/logout']")
	WebElement LogoutBTN;



	public LoginUser(WebDriver driver){
		PageFactory.initElements(driver,this); // test Object
	}


	public void verifyHeading()
	{
		String Exptitle = "Login to your account";
		System.out.println("Title is : "+LoginText.getText());

		Assert.assertEquals(LoginText.getText(), Exptitle, "Title Dosent Match user is not on Home Page");
	}
	public void Login(String Email, String Pswd)
	{
		email.sendKeys(Email);//TestUser1@gmail.com
		password.sendKeys(Pswd);
		loginBTN.click();
	}

	public void Validationmessage()
	{
		String Expmessage = "Your email or password is incorrect!";
		System.out.println("message displayed : "+validationmsgTxt.getText());

		Assert.assertEquals(validationmsgTxt.getText(), Expmessage, "validation Message not same");
	}
	public void Logout()
	{
		LogoutBTN.click();
	}


}
