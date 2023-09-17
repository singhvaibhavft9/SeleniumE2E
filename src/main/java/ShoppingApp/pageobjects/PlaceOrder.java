package ShoppingApp.pageobjects;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingApp.AbstractComponents.AbstractComponent;

public class PlaceOrder extends AbstractComponent {
	
	WebDriver driver;
	

	public PlaceOrder(WebDriver driver) {	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> listofCountries;
	
	
	@FindBy(css=".actions a")
	WebElement placeOrder;
	
	@FindBy(css=".hero-primary")
	WebElement thankyouText;
	
	
	By visibileConfirmation= By.xpath("//div[@id='toast-container']");
	
	
	
	public void sendCountry(String countryName)
	{
		Actions act = new Actions(this.driver);
		act.sendKeys(selectCountry, countryName).build().perform();
	}
	
	public void selectCountryFromOptions(String countryName)
	{
		listofCountries.stream()
		 .filter(Option->Option.getText().equals(countryName)).collect(Collectors.toList()).get(0).click();
		
		
	}
	
	public ConfirmationPage placeOrderButton()
	{
		placeOrder.click();
		waitForTheElementToAppear(visibileConfirmation);
		return new ConfirmationPage(driver);
	}
	
	
	
	
	
	
	
	
	
	


}
