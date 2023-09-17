package ShoppingApp.pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShoppingApp.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	


	public LandingPage(WebDriver driver) {
		
		super(driver); //we need to give life to the parent class driver to AbstractComponent
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	private WebElement userName;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement passWord;
	
	@FindBy(css="input[name='login']")
	WebElement submit;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	
	public void goToURL()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalog loginApplication(String username, String password )
	{
		userName.sendKeys(username);
		passWord.sendKeys(password);
		submit.click();
		return new ProductCatalog(driver);
	}
	
	public String validateError()
	{
		waitForTheElementToAppear(errorMessage);
		return errorMessage.getText();
	}


}
