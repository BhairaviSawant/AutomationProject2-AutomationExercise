package pageElementsFile;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecommendedItems {

	private WebDriver driver;


	@FindBy(xpath ="//h2[contains(text(), 'recommended items')]")
	public WebElement ScrolltoRecommendedtext;

	 @FindBy(xpath = "//div[@class='item active']//div[@class='item active']//p")
	    private List<WebElement> RecommendedproductTitles;

	 @FindBy(xpath = "//div[@class='item active']//a[contains(text(),'Add to cart')]")
	    private List<WebElement> RecommendedAddToCartButtons;

	 @FindBy(xpath= " //button[text()='Continue Shopping']")
	    private WebElement continueShopButton;

	 @FindBy(xpath = "//div[@class='recommended_items']")
	    private WebElement recommendedItemsSection;




	public RecommendedItems(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	public boolean isRecommendedItemsVisible() {
        return recommendedItemsSection.isDisplayed();
    }

	public void addAllRecommendedProductsToCart() throws InterruptedException {
		System.out.println("addAllProductsToCart() in productpage");
        for (WebElement productImage :RecommendedAddToCartButtons ) {
            System.out.println("Adding product: " + productImage.getText() + " to cart...");
            productImage.click();
            Thread.sleep(1000);
        	continueShopButton.click();

        }
    }

	public void GetRecommendedProductList()
	{
		 List<WebElement> AllProductList = driver.findElements(By.xpath("//div[@class='recommended_items']//div[@class='item active']//p"));
		 System.out.println(" RECOMMENDED PRODUCT COUNT: " +AllProductList.size());
	        for (WebElement productImage : AllProductList) {

	            String ProductText = productImage.getText();
	            System.out.println("Product Text: " + ProductText);
	}
	}


}
