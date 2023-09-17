package ShoppingApp.pageobjects;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingApp.AbstractComponents.AbstractComponent;

public class MyOrderList extends AbstractComponent {
	
   WebDriver driver;
	

	public MyOrderList(WebDriver driver) {	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderlist;
	
	By orderTable = By.tagName("tbody");
	
	public List<WebElement> getOrderList()
	{
		waitForTheElementToAppear(orderTable);
		return orderlist;
	}
	
	public boolean getOrderByName(String orderName)
	{
		return getOrderList().stream().anyMatch(order->order.getText().equalsIgnoreCase(orderName));
		
    }

}
