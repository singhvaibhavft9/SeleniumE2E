package ShoppingApp.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ShoppingApp.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public  WebDriver driver;
	
	public LandingPage lp;
	
	public  WebDriver initialize( ) throws IOException
	{
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//ShoppingApp//Resources//GlobalData.properties");
		prop.load(fis);
		
		
		String browser= System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
				
		
		if(browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			 driver = new EdgeDriver(options);					
		}
		
		else if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();		
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
         return driver;
	}
	
	@BeforeMethod (groups={"Purchase"})
	public  LandingPage  launchApplication() throws IOException
	{
		driver=initialize();
		 lp= new LandingPage(driver);
		lp.goToURL();
		return lp;
		
		
	}
	
	@AfterMethod (groups={"Purchase"})
	public void tearDown()
	{
		System.out.println("Closing Browser");
		//driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap( String filePath) throws IOException
	{
		//read json to String
		String jsonFormatFile =FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);
		
		//Convert String to HashMap
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> data = mapper.readValue(jsonFormatFile, new TypeReference<List<HashMap<String,String>>>() {
			
		});
		return data;
	}
	
	public String takeScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;	
	   File source=	ts.getScreenshotAs(OutputType.FILE);
	   File file= new File(System.getProperty("user.dir")+ "//reports//" +testCaseName + ".png");
	   FileUtils.copyFile(source, file);
	   return System.getProperty("user.dir")+ "//reports//" +testCaseName + ".png";
	}

}
