package pageElementsFile;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductPage {

	private WebDriver driver;
    private Actions actions;
    WebDriverWait wait;


	@FindBy(xpath ="//a[@href='/products']")
	WebElement Productlink;

	@FindBy(xpath ="//h2[text()='All Products']")
	WebElement productText;

	@FindBy(xpath ="//a[@href= '/product_details/1']")
	WebElement Firstprod;

	@FindBy(className ="product-information")
	WebElement Productinfo;

	@FindBy(id = "search_product")
	WebElement  Productname;
	@FindBy(id ="submit_search")
	WebElement SearchBtn;
	@FindBy(xpath ="//a[@href='#reviews']")
	WebElement WriteReviewText;

	@FindBy(xpath ="//h2[text()='Searched Products']")
	WebElement SearchProdText;

	@FindBy(xpath = "//img[@src='/get_product_picture/1']")
    private WebElement firstProductCartButton;

    @FindBy(xpath = "//img[@src='/get_product_picture/2']")
    private WebElement secondProductCartButton;

    @FindBy(xpath= " //button[text()='Continue Shopping']")
    private WebElement continueShopButton;

    @FindBy(xpath = "//a[@href='/view_cart']")
    private WebElement viewCartLink;

    @FindBy(xpath = "//u[text()='View Cart']")
    private WebElement ViewCartTextButton;

    @FindBy(xpath = "//div[contains(@class, 'productinfo')]/p")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//div[contains(@class, 'productinfo')]/a")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//a[@data-product-id='1']")
    private WebElement AddtoCartOneButton;

    @FindBy(xpath = "//a[@data-product-id='1' and normalize-space()='Add to cart']")
    private WebElement AddtoCartOne;


    @FindBy(xpath = "//a[@data-product-id='2']")
    private WebElement AddtoCartTwoButton;

    @FindBy(xpath = "//tr[contains(@id, 'product-')]")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//a[@href='/product_details/5']")
    private WebElement Viewprodbtn;

    @FindBy(id = "quantity")
    private WebElement QuantityEle;

    @FindBy(xpath = "//button[contains(@class, 'cart')]")
    private WebElement AddcartBtn;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement EnterNameinReviewForm;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement EnterEmailonReviewForm;
    @FindBy(xpath = "//textarea[@id='review']")
    private WebElement EnterReviewonReviewForm;

    @FindBy(xpath = "//button[@id='button-review']")
    private WebElement ClickSubmitonReviewForm;

    @FindBy(xpath = "//span[text()='Thank you for your review.']")
    private WebElement VerifyThankYouMessage;


	public ProductPage(WebDriver driver){
		this.driver = driver;
		 this.actions = new Actions(driver);
		PageFactory.initElements(driver,this);
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	}

	public void ClickProduct()
	{
		Productlink.click();
	}

	public void VerifyProductpage()
	{
		String ExpectedText = "ALL PRODUCTS";
		wait.until(ExpectedConditions.visibilityOf(productText));

		Assert.assertEquals(productText.getText(), ExpectedText, "Text not visible");
	}

	public void GetProductList()
	{
		 List<WebElement> AllProductList = driver.findElements(By.xpath("//div[@class='productinfo text-center']"));
		 System.out.println("PRODUCT COUNT: " +AllProductList.size());
	        for (WebElement productImage : AllProductList) {

	            String ProductText = productImage.getText();
	            System.out.println("Product Text: " + ProductText);
	}
	}

	public void addAllProductsToCart() throws InterruptedException {
		System.out.println("addAllProductsToCart() in productpage");
        for (int i = 0; i < productTitles.size(); i++) {
            System.out.println("Adding product: " + productTitles.get(i).getText() + " to cart...");
            addToCartButtons.get(i).click();
    		wait.until(ExpectedConditions.visibilityOf(continueShopButton));

            continueShopButton.click();

        }
    }


	public void Firstproduct()
	{
		Firstprod.click();
		//	9. Verify that detail detail is visible: product name, category, price, availability, condition, brand
		System.out.println(" First Product details : "+"\n"+Productinfo.getText());
	}

	public void SearchProduct(String Prod)
	{
		//String Prod1 = "winter top";
		Productname.sendKeys(Prod);
		SearchBtn.click();
	}
	public void VerifySearchProductText()
	{
		String ExpectedText = "SEARCHED PRODUCTS";
		Assert.assertEquals(SearchProdText.getText(), ExpectedText, "Text not visible");
	}

	public void VerifyWriteYourReviewText()
	{
		String ExpectedText = "WRITE YOUR REVIEW";
		Assert.assertEquals(WriteReviewText.getText(), ExpectedText, "Text not visible");
	}

	public void FirstProductAddToCart() throws InterruptedException {

		actions.moveToElement(firstProductCartButton).perform();
		wait.until(ExpectedConditions.visibilityOf(AddtoCartOneButton));

		AddtoCartOneButton.click();
    }

    public void SecondProductAddToCart() {
    	actions.moveToElement(secondProductCartButton).perform();
    	AddtoCartTwoButton.click();
    }
    public void AddFirstProducttoCart() throws InterruptedException {
    AddtoCartOne.click();
   
    }
    public void clickContinueShopBTN() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(continueShopButton));

    	continueShopButton.click();
    }

    public void clickViewCartButton() {
    	viewCartLink.click();
    }

    public void ClickViewCartText() {
    	ViewCartTextButton.click();

    }

    public int getNumberOfItemsInCart() {
        return cartItems.size();
    }
    public String getProductPrice(int index) {
        WebElement priceElement = cartItems.get(index).findElement(By.xpath(".//td[@class='cart_price']/p"));
        return priceElement.getText();
    }

    public String getProductQuantity(int index) {
        WebElement quantityElement = cartItems.get(index).findElement(By.xpath(".//button[@class='disabled']"));
        return quantityElement.getAttribute("value");
    }

    public String getProductTotalPrice(int index) {
        WebElement totalPriceElement = cartItems.get(index).findElement(By.xpath(".//td[@class='cart_total']/p"));
        return totalPriceElement.getText();
    }

    public void viewProduct() {
    	Viewprodbtn.click();
		wait.until(ExpectedConditions.visibilityOf(Productinfo));

		System.out.println(" Product details : "+"\n"+Productinfo.getText());

    }
    public void AddProductQuantity(String Quantity)
	{
    	QuantityEle.clear();
    	QuantityEle.sendKeys(Quantity);
	}
    public void Addcartbtn()
    {
    	AddcartBtn.click();
    }

    public void EnterReviewDetails(String Name,String Email,String review) throws InterruptedException
    {

    	EnterNameinReviewForm.sendKeys(Name);
    	EnterEmailonReviewForm.sendKeys(Email);
    	EnterReviewonReviewForm.sendKeys(review);
		wait.until(ExpectedConditions.visibilityOf(ClickSubmitonReviewForm));
		ClickSubmitonReviewForm.click();
    }

    public void VerifyThankYouMessage()
    {
    	String ExpectedText = "Thank you for your review.";
		Assert.assertEquals(VerifyThankYouMessage.getText(), ExpectedText, "Text not visible");
    }
}
