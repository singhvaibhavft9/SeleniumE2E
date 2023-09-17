package ShoppingApp.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ShoppingApp.TestComponents.BaseTest;
import ShoppingApp.pageobjects.Checkout;
import ShoppingApp.pageobjects.ConfirmationPage;
import ShoppingApp.pageobjects.LandingPage;
import ShoppingApp.pageobjects.MyOrderList;
import ShoppingApp.pageobjects.PlaceOrder;
import ShoppingApp.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	
	
		@Test (dataProvider="getData",groups={"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException {
		ProductCatalog pc=lp.loginApplication(input.get("email"), input.get("password"));
				
		pc.addItemToCart(input.get("productName"));
		
		Checkout co=pc.goToCart();
		
	    boolean match=co.matchItem(input.get("productName"));
	    
	    Assert.assertTrue(match);
	    
	    PlaceOrder po=co.clickCheckout();
	    
	    po.sendCountry("India");
	    po.selectCountryFromOptions("India");
	    
	   ConfirmationPage cp= po.placeOrderButton();
	   
	    
	    String confirmThankYou= cp.thankYouConfirmation();
	    
	    Assert.assertTrue(confirmThankYou.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
	    
		 }
		
		
		@Test(dependsOnMethods={"submitOrder"})
		public void verifyOrderList()
		{
			lp.loginApplication("sinvaib123@gmail.com", "Vaibhav@12345");
			MyOrderList Ol=lp.goToOrders();
			boolean matchOrder=Ol.getOrderByName("ZARA COAT 3");
			Assert.assertTrue(matchOrder);
		}
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
//			HashMap<Object,Object> map = new HashMap<Object,Object>();
//			map.put("email", "sinvaib123@gmail.com");
//			map.put("password", "Vaibhav@12345");
//			map.put("productName", "ZARA COAT 3");
//			
//			HashMap<Object,Object> map1 = new HashMap<Object,Object>();
//			map1.put("email", "somej@gmail.com");
//			map1.put("password", "Somej@123");
//			map1.put("productName", "ADIDAS ORIGINAL");
			
//			C:\Users\Vaibhav Singh\eclipse-workspace\SeleniumFrameworkDesign\src\test\java\ShoppingApp\Data\PurchaseOrder.json
			
			 List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//ShoppingApp//Data//PurchaseOrder.json");
			
			 return new Object[][] {{data.get(0)},{data.get(1)}};
			
			
			
			//return new Object[][]  {{"sinvaib123@gmail.com","Vaibhav@12345","ZARA COAT 3"},{"somej@gmail.com","Somej@123","ADIDAS ORIGINAL"}};
		}
		
		

}