package ShoppingApp.test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ShoppingApp.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
		
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		String myEmail = "sinvaib123@gmail.com";
		String myPwd="Vaibhav@12345";
		
		LandingPage lp= new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys(myEmail);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(myPwd);
		driver.findElement(By.cssSelector("input[name='login']")).click();
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
				
		WebElement element1 = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		element1.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
		
		List<WebElement> cartItems= driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match =  cartItems.stream().anyMatch(Item->Item.getText().equalsIgnoreCase("ZARA COAT 3"));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions act = new Actions(driver);
		
		act.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();

		
		 driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']")).stream()
				 .filter(Option->Option.getText().equals("India")).collect(Collectors.toList()).get(0).click();
		 
		 driver.findElement(By.cssSelector(".actions a")).click();
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		 
		 String thankYouText=driver.findElement(By.cssSelector(".hero-primary")).getText();
		 
		 Assert.assertTrue(thankYouText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		 
		
		 
		
		

}
}