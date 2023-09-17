package ShoppingApp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingApp.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	
	WebDriver driver;
	

	public ProductCatalog(WebDriver driver) {	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement nGAnimation;
	
	By productList= By.cssSelector(".mb-3");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMessage= By.xpath("//div[@id='toast-container']");
	
	
	public List<WebElement> getProductList()
	{
		waitForTheElementToAppear(productList);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
    }
	
	public void addItemToCart(String productName)
	{
		WebElement prod=getProductByName(productName);
		
		prod.findElement(addToCart).click();
		waitForTheElementToAppear(toastMessage);
		waitforTheElementToDisappear(nGAnimation);
		
	}
	
	
	
	


}
