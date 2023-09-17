package ShoppingApp.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	
	public static ExtentReports getReport()
	{
		String path= System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
			reporter.config().setReportName("Web Automation Suite");
			reporter.config().setDocumentTitle("Regression Pack");
			
			ExtentReports extent= new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Vaibhav");
			return extent;
			
	}

}
