package pageElementsFile;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPageElements {

	private WebDriver driver;

	@FindBy(xpath ="//a[@href='/view_cart']")
	WebElement Carttlink;

	@FindBy(xpath ="//li[text()='Shopping Cart']")
	WebElement ShoppingCartText;

	@FindBy(xpath ="//a[text()='Proceed To Checkout']")
	WebElement ProceedCheckOutBTN;

	@FindBy(xpath ="//ul[@id='address_invoice']")
	WebElement VerifyAddressDetails;

	@FindBy(name ="message")
	WebElement EnterDescription;

	@FindBy(xpath ="//a[@href='/payment']")
	WebElement PaymentBtn;

	@FindBy(name ="name_on_card")
	WebElement EnterName;

	@FindBy(xpath ="//input[@name='card_number']") //card_number
	WebElement EnterCardNumber;

	@FindBy(name ="cvc")
	WebElement EnterCVC;

	@FindBy(name ="expiry_month")
	WebElement EnterExpiryMonth;

	@FindBy(xpath ="//input[@name='expiry_year']")
	WebElement EnterExpiryYear;  //

	@FindBy(xpath ="//button[@id='submit']")
	WebElement PayandConfrimOrderBtn;

	@FindBy(xpath  ="//u[text()='Register / Login']")
	WebElement RegisterBtn;

	@FindBy(xpath ="//div[contains(text(), 'Your order has been placed successfully!')]")
	WebElement SuccessMessageOrderplaced;

	@FindBy(xpath ="//p[text()='Congratulations! Your order has been confirmed!']")
	WebElement SuccessMessageOrderConfrimed;



	@FindBy(className ="cart_quantity_delete")
	WebElement DeleteProductBtn;

	@FindBy(xpath ="//p[@class='text-center']/b")
	WebElement CartEmptyText;

	@FindBy(id  ="empty_cart")
	WebElement VerifyTextCartEmpty;


	@FindBy(id ="address_delivery")
	WebElement deliveryAddressElement;

	@FindBy(xpath ="//ul[@id='address_invoice']")
	WebElement billingAddressElement;


	@FindBy(xpath = "//a[contains(text(),'Download Invoice')]")
	private WebElement downloadInvoiceButton;

	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement OrderPlacedContinueBtn;
	public CartPageElements(WebDriver driver){
		PageFactory.initElements(driver,this); // test Object
	}


	public void ClickProduct()
	{
		Carttlink.click();
	}

	public void VerifyShoppingCartpage()
	{
		String ExpectedText = "Shopping Cart";
		Assert.assertEquals(ShoppingCartText.getText(), ExpectedText, "Text not visible");
	}

	public void ClickProceedCheckOutBTN()
	{
		ProceedCheckOutBTN.click();
	}

	public void VerifyAddressDetails()
	{
		System.out.println("Address Details ::"+VerifyAddressDetails.getText());
	}

	public void EnterDescriptionTextbox(String Text)
	{
		EnterDescription.sendKeys(Text);
	}

	public void ClickPlaceOrderBTN()
	{
		PaymentBtn.click();
	}
	public void EnterCardDetails(String Name, String CardNumber, String CVC, String ExpMonth, String ExpYear)
	{
		EnterName.sendKeys(Name);
		EnterCardNumber.sendKeys(CardNumber);
		EnterCVC.sendKeys(CVC);
		EnterExpiryMonth.sendKeys(ExpMonth);
		EnterExpiryYear.sendKeys(ExpYear);
	}

	public void ClickpaymentConfrimOrder()
	{
		PayandConfrimOrderBtn.click();
	}

	public void VerifySuccessmessageOrderConfrim()
	{
		String ExpectedText = "Your order has been placed successfully!";
		Assert.assertEquals(SuccessMessageOrderplaced.getText(), ExpectedText, "Text not visible");
		//.
	}

	public void VerifyOrderSuccessmessage()
	{
		String ExpectedText = "Congratulations! Your order has been confirmed!";
		System.out.println(SuccessMessageOrderConfrimed.getText()+"Is Displayed");
		Assert.assertEquals(SuccessMessageOrderConfrimed.getText(), ExpectedText, "Text not visible");
		//.
	}
	public void ClickonRegister()
	{
		RegisterBtn.click();
	}

	public void ClickonDeletebtn()
	{
		DeleteProductBtn.click();
	}

	public void VerifyCartEmptyText()
	{
		String ExpectedText = "Cart is empty!";

		System.out.println(VerifyTextCartEmpty.getText());
		//Assert.assertEquals(VerifyTextCartEmpty.getText(), ExpectedText, "Text not visible");
		Assert.assertEquals(CartEmptyText.getText(), ExpectedText, "Text not visible");

	}

	public String getDeliveryAddress() {
		return deliveryAddressElement.getText();
	}

	// Method to get billing address
	public String getBillingAddress() {
		return billingAddressElement.getText();
	}

	// Method to click on Download Invoice button
	public void clickDownloadInvoiceButton() {
		downloadInvoiceButton.click();
	}

	public void OrderPlacedContinueBtn() {
		OrderPlacedContinueBtn.click();
	}

	// Method to check if the invoice is downloaded
	public boolean isInvoiceDownloadedSuccessfully(String fileName) {
		File file = new File(System.getProperty("user.home") + "/Downloads/" + fileName);
		return file.exists();
	}
}
