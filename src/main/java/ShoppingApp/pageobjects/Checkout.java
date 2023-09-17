package ShoppingApp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ShoppingApp.AbstractComponents.AbstractComponent;

public class Checkout extends AbstractComponent {
	
	WebDriver driver;
	

	public Checkout(WebDriver driver) {	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css=".cartSection h3")
	List<WebElement> listOfCheckOutItems;
	
	
	
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	

	
	public PlaceOrder clickCheckout()
	{
		checkOut.click();
		return new PlaceOrder(driver);
	}
	
	public boolean matchItem(String productName)
	{
		return listOfCheckOutItems.stream().anyMatch(Item->Item.getText().equalsIgnoreCase(productName));		
		
	}
	
	
	


}
