package pageElementsFile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class RegisterUser {

	private WebDriver driver;

	@FindBy(xpath ="//a[@href='/login']")
	WebElement SignupBtn;

	@FindBy(xpath ="//h2[text()='New User Signup!']")
	WebElement SignupText;

	@FindBy(xpath ="//b[text()='Enter Account Information']")
	WebElement VerifyText;

	@FindBy(name ="name")
	WebElement name;

	@FindBy(xpath ="//input[@data-qa ='signup-email']")
	WebElement email;

	@FindBy(xpath ="//button[text() ='Signup']")
	WebElement SignUpBTN;

	//9. Fill details: Title, Name, Email, Password, Date of birth

	@FindBy(xpath ="//input[@value='Mrs']")
	WebElement Radio;
	// Element in ENTER ACCOUNT INFORMATION Form
	@FindBy(id= "name")
	WebElement NAME;

	@FindBy(id ="email")
	WebElement EMAIL;

	@FindBy(id ="password")
	WebElement PSWD;

	@FindBy(id ="days")
	WebElement DateDropdown;

	@FindBy(id ="months")
	WebElement MonthDropdown;

	@FindBy(id ="years")
	WebElement YearDropdown;

	// select Checkbox
	@FindBy(id ="newsletter")
	WebElement newsletterCB ;

	@FindBy(id ="optin")
	WebElement specialOffersCB;

	// Enter Address Info

	@FindBy(id= "first_name")
	WebElement FNAME;
	@FindBy(id= "last_name")
	WebElement LNAME;

	@FindBy(id ="company")
	WebElement Company;

	@FindBy(id= "address1")
	WebElement Addone;
	@FindBy(id= "address2")
	WebElement Addtwo;

	@FindBy(id ="country")
	WebElement Country;

	@FindBy(id= "state")
	WebElement State;
	@FindBy(id= "city")
	WebElement City;
	@FindBy(id= "zipcode")
	WebElement ZipCode;
	@FindBy(id= "mobile_number")
	WebElement MobileNo;
	@FindBy(xpath= "//button[@data-qa= 'create-account']")
	WebElement createActnBtn;

	@FindBy(xpath ="//b[text()='Account Created!']")
	WebElement VerifyAccCreatedText;
	@FindBy(xpath= "//a[@data-qa= 'continue-button']")
	WebElement ContinueBtn;

	@FindBy(xpath= "//a[@href='/delete_account']")
	WebElement DeleteAccBtn;

	@FindBy(xpath= "//h2[@data-qa= 'account-deleted']")
	WebElement DeleteAccText;



	//a[contains(text(), 'Logged in as')]/i[contains(@class, 'fa fa-user')]
	@FindBy(xpath ="//a[contains(text(), 'Logged in as')]")
	WebElement VerifyAccounttext;

	@FindBy(xpath ="//p[text()='Email Address already exist!']")
	WebElement UserExistsText;

	public RegisterUser(WebDriver driver){
		PageFactory.initElements(driver,this); // test Object
	}

	public void verifyHome(String Actualtitle)
	{
		String Exptitle = "Automation Exercise";
		Assert.assertEquals(Actualtitle, Exptitle, "Title Dosent Match user is not on Home Page");
	}

	public void Signup()
	{
		SignupBtn.click();
	}

	public void verifyHeading()
	{
		String Exptitle = "New User Signup!";
		System.out.println("Text visible is : "+SignupText.getText());

		Assert.assertEquals(SignupText.getText(), Exptitle, "Title Dosent Match user is not on Home Page");
	}

	public void EnterDetails(String Name, String Email)
	{
		name.sendKeys(Name);
		email.sendKeys(Email);
		SignUpBTN.click();
	}

	public void verifyHeadingAccountInfo()
	{
		String Exptitle = "ENTER ACCOUNT INFORMATION";

		System.out.println("Text visible is :"+VerifyText.getText());

		Assert.assertEquals(VerifyText.getText(), Exptitle, "ENTER ACCOUNT INFORMATION Text Didnt match");
	}

	public void EnterAccountInfo(String Name, String Email, String Pwd) throws InterruptedException
	{
		Thread.sleep(3000);
		Radio.click();
		//NAME.sendKeys(Name);
		//EMAIL.sendKeys(Email);
		PSWD.sendKeys(Pwd);

		// Select day, month, and year from dropdowns
	    Select daySelect = new Select(DateDropdown);
	    daySelect.selectByVisibleText("15");

	    Select monthSelect = new Select(MonthDropdown);
	    monthSelect.selectByVisibleText("February");

	    Select yearSelect = new Select(YearDropdown);
	    yearSelect.selectByVisibleText("1997");
	}

	public void CheckboxSelect()
	{
		// Select the checkbox 'Sign up for our newsletter!'
        if (!newsletterCB.isSelected()) {
        	newsletterCB.click();
        }

        // Select the checkbox 'Receive special offers from our partners!'
        if (!specialOffersCB.isSelected()) {
        	specialOffersCB.click();
        }
	}

	public void AddressDetails(String FName, String Lname, String company,String Add1, String Add2, String state, String Cityname, String zipcode,String MobNo) throws InterruptedException {

		FNAME.sendKeys(FName);
		LNAME.sendKeys(Lname);
		Company.sendKeys(company);
		Addone.sendKeys(Add1);
		Addtwo.sendKeys(Add2);

		Select CountryDD = new Select(Country);
		CountryDD.selectByVisibleText("India");
		State.sendKeys(state);
		City.sendKeys(Cityname);
		ZipCode.sendKeys(zipcode);
		MobileNo.sendKeys(MobNo);

		createActnBtn.click();
	}

	public void verifyHeadingAccountCreated()
	{
		String Exptitle = "ACCOUNT CREATED!";

		System.out.println("Text visible is :"+VerifyAccCreatedText.getText());

		Assert.assertEquals(VerifyAccCreatedText.getText(), Exptitle, "Text Didnt match");
		VerifyAccCreatedText.click();
		
		ContinueBtn.click();

		//driver.switchTo().alert().dismiss();

	}

	public void VerifyuserId() throws InterruptedException
	{
		String ExpLinktitle = "Logged in as";
		String ActualText = VerifyAccounttext.getText();
		System.out.println("Text visible is : "+VerifyAccounttext.getText());

		// Verify if the actual text contains the expected partial text
        if (ActualText.contains(ExpLinktitle)) {
            System.out.println("Partial text verification successful.");
        } else {
            System.out.println("Partial text verification failed.");
        }
	}

	public void Verifydelete()
	{
		DeleteAccBtn.click();
		String Exptitle = "ACCOUNT DELETED!";

		System.out.println("Text visible is :"+DeleteAccText.getText());

		Assert.assertEquals(DeleteAccText.getText(), Exptitle, "Text Didnt match");
		ContinueBtn.click();

	}

	public void VerifyUserExists()
	{
		String Exptitle = "Email Address already exist!";

		System.out.println("Text visible is :"+UserExistsText.getText());

		Assert.assertEquals(UserExistsText.getText(), Exptitle, "Email Address already exist! Not present");


	}


}
