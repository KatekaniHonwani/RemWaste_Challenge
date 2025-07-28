package Functions;

import java.time.Duration;

//import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	protected WebDriver driver;
	public static ExtentReports extent;
	public static ExtentSparkReporter htmlReporter;
	 public static ExtentTest test;
	//public static Logger logger;
	
	@BeforeMethod
	public void Setup() {
		
		
	    WebDriverManager.chromedriver().setup();
	    
	    ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);

		
		driver.get("https://in.bookmyshow.com/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		
	}
	
	
	 public void initializeReport(){
		 
		 ExtentSparkReporter htmlReporter =  new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/REMWasteExtentReport.html");
		    htmlReporter.config().setDocumentTitle("REMWaste Automation Report Order Pizza");
		    htmlReporter.config().setReportName("REMWaste Test Report");
		    htmlReporter.config().setTheme(Theme.STANDARD);
		    htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		    extent = new ExtentReports();
		 
		    extent.attachReporter(htmlReporter);   
		}
	 
	
	@AfterMethod
	public void tearDown() {
		
		//driver.close();
		
		extent.flush();
		
	}

}
