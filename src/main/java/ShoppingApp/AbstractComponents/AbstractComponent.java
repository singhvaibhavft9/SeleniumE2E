package ShoppingApp.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ShoppingApp.pageobjects.Checkout;
import ShoppingApp.pageobjects.MyOrderList;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[routerlink*=cart]")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*=myorders]")
	WebElement orderButton;
	
	
	//This checkout the Cart Items
	public Checkout goToCart()
	{
		Checkout co= new Checkout(driver); 
		cartButton.click();
		return co;
		
		
	}
	
	public MyOrderList goToOrders()
	{
	   MyOrderList Ol= new MyOrderList(driver);
	   orderButton.click();
	   return Ol;
	}

	public void waitForTheElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForTheElementToAppear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitforTheElementToDisappear( WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOf(element));
		
	}
	

}
