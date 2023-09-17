package ShoppingApp.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ShoppingApp.TestComponents.BaseTest;
import ShoppingApp.TestComponents.Retry;
import ShoppingApp.pageobjects.Checkout;
import ShoppingApp.pageobjects.LandingPage;
import ShoppingApp.pageobjects.ProductCatalog;

public class ErrorValidation extends BaseTest {
	
	@Test(retryAnalyzer=Retry.class)
	public void submitOrderFailed() throws IOException {
	lp.loginApplication("sinvaib123@gmail.com", "Vaibhav@12245");
	Assert.assertEquals("Incorrect email or password.", lp.validateError());

	}
	
	@Test
	public void productValidation()
	{		
		ProductCatalog pg=lp.loginApplication("sinvaib123@gmail.com", "Vaibhav@12345");
		pg.addItemToCart("ZARA COAT 3");		
		Checkout co=pg.goToCart();		
	    boolean match=co.matchItem("ZARA COAT 33");	    
	    Assert.assertFalse(match);
		
		
	}

}

