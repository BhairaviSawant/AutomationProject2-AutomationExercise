package pageElementsFile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePageElements {

	private WebDriver driver;

	@FindBy(id="susbscribe_email")
	WebElement EnterSubscriptionText;

	@FindBy(xpath="//h2[text()='Subscription']")
	WebElement SubText;

	@FindBy(id="subscribe")
	WebElement subscribeBtn;

	@FindBy(xpath="//div[@class='alert-success alert']")
	WebElement SuccessAlret;

	@FindBy(xpath ="//a[@href='/view_cart']")
	WebElement CartBtnLink;

	@FindBy(className="left-sidebar")
	WebElement LeftSidebarText;

	@FindBy(xpath="//div[@class='left-sidebar']")
	WebElement categoriesSidebar;

	@FindBy(xpath="//a[@href='#Women']")
	WebElement womenCategoryLink;


	@FindBy(xpath="//a[@href='#Men']")
	WebElement mensCategoryLink;

	@FindBy(xpath="//a[text()='Dress ']")
	WebElement selectWomenSubcategory;

	@FindBy(xpath="//a[text()='Tshirts ']")
	WebElement selectMensSubcategory;

	@FindBy(xpath="//h2[text()='Women - Dress Products']")
	WebElement VerifyWomenCategoryHeading;

	@FindBy(xpath="//h2[text()='Men - Tshirts Products']")
	WebElement VerifyMenCategoryHeading;

	@FindBy(xpath="//h2[text()='Full-Fledged practice website for Automation Engineers']")
	WebElement VerifyHeaderTextOnTOP;



	// For brand wise Products

	@FindBy(xpath="//a[text()='Polo']")
	WebElement brandProductLinkone;

	@FindBy(xpath="//a[text()='Biba']")
	WebElement brandProductLinktwo;

	@FindBy(xpath="//div[@class='brands_products']")
	WebElement BrandsSidebar;

	@FindBy(xpath="//h2[text()='Brand - Polo Products']")
	WebElement verifyPoloBrandHeading;

	@FindBy(xpath="//h2[text()='Brand - Biba Products']")
	WebElement verifyBibaBrandHeading;
//    private By categoriesSidebar = By.xpath("//div[@class='left-sidebar']");
//    private By womenCategoryLink = By.xpath("//a[@href='#Women']");

//	@FindBy(xpath="//a[@href='#top']")
//	WebElement NavigateTopBtn;

	@FindBy(xpath="//a[@id='scrollUp']")
	WebElement NavigateTopBtn;

	public HomePageElements(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public void VerifySubscription()
	{

		String ExpectedText = "SUBSCRIPTION";
		Assert.assertEquals(SubText.getText(), ExpectedText, "Text not visible");

	}

	public void EnterSubscriptionEmail(String Email)
	{
		EnterSubscriptionText.sendKeys(Email);
		subscribeBtn.click();
	}

	public void VerifySuccessText()
	{
		System.out.println("Suscces Message Displayed :"+ SuccessAlret.getText());
		String ExpectedText = "You have been successfully subscribed!";
		Assert.assertEquals(SuccessAlret.getText(), ExpectedText, "Text not visible");

	}

	public void ClickCart()
	{
		CartBtnLink.click();
	}

	// for categories wise product
    public boolean VerifyCategoriesVisible()
    {
    	return  categoriesSidebar.isDisplayed();
    }

    public void clickWomenCategory()
    {
    	womenCategoryLink.click();
    }

    public void clickWomenDress()
    {
    	selectWomenSubcategory.click();
    }

    public void clickMenCategory()
    {
    	mensCategoryLink.click();
    }
    public void clickMensSubOption()
    {
    	selectMensSubcategory.click();
    }

    public void VerifyWomensCategoryHeadingText()
	{
		System.out.println("Category Displayed :"+ VerifyWomenCategoryHeading.getText());
		String ExpectedText = "WOMEN - DRESS PRODUCTS";
		Assert.assertEquals(VerifyWomenCategoryHeading.getText(), ExpectedText, "Text not visible");

	}
    public void VerifyMensCategoryHeadingText()
	{
		System.out.println("Category Displayed :"+ VerifyMenCategoryHeading.getText());
		String ExpectedText = "MEN - TSHIRTS PRODUCTS";
		Assert.assertEquals(VerifyMenCategoryHeading.getText(), ExpectedText, "Text not visible");

	}
    // For Brand wise Product
    public boolean VerifyBrandsVisible()
    {
    	return  categoriesSidebar.isDisplayed();
    }
    public void clickPoloBrand()
    {
    	brandProductLinkone.click();
    }
    public void clickBIBABrand()
    {
    	brandProductLinktwo.click();
    }

  public void VerifyPolobrandName()
	{
		System.out.println("Category Displayed :"+ verifyPoloBrandHeading.getText());
		String ExpectedText = "Brand - Polo Products";
		Assert.assertEquals(verifyPoloBrandHeading.getText(), ExpectedText, "Text not visible");

	}

    public void VerifyBibabrandName()
	{
		System.out.println("Category Displayed :"+ verifyBibaBrandHeading.getText());
		String ExpectedText = "BRAND - BIBA PRODUCTS";
		Assert.assertEquals(verifyBibaBrandHeading.getText(), ExpectedText, "Text not visible");

	}

    public void NavigatetoTop() throws InterruptedException
	{	Thread.sleep(1000);
    	NavigateTopBtn.click();
	}
    public void VerifyHeaderTextOnTOP()
   	{
   		System.out.println("Heading Displayed :"+ VerifyHeaderTextOnTOP.getText());
   		String ExpectedText = "Full-Fledged practice website for Automation Engineers";
   		Assert.assertEquals(VerifyHeaderTextOnTOP.getText(), ExpectedText, "Header text");

   	}

}
