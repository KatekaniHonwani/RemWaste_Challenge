package Functions;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
//import junit.framework.Assert;
import com.relevantcodes.extentreports.NetworkMode;

//import gui.Functions.NGBSS.NGBSS_Datafunctions;
//import gui.Functions.NGBSS.NGBSS_Utilities;

public class UtilityFunctions {

	//static DataFunctions2 data = new DataFunctions2();
	private String processName;
	public static  File outputFile;
	public static String pathToSubfolder;
	public static String pathToParentScreenshotfolder;
	
//testing as life


	public String FolderCreate(String stestFolderName,String sDefaultPath)
	{
		
		try
		
		{
			java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
			
			if (!localMachine.getHostName().toUpperCase().contains("MS-N-")){
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				ScreenshotParentFolder(stestFolderName.trim().replace(" ", "_")+"_" + timeStamp,sDefaultPath);					
			
		
			}else{
			     sDefaultPath = (System.getProperty("user.dir")+"\\report\\jenkins-report\\WebContent\\images");
			     String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			     ScreenshotParentFolder(stestFolderName.trim().replace(" ", "_")+"_" + timeStamp,sDefaultPath);
			 
			}
		
			

		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
	
	
		return sDefaultPath;
	}
	/*******************************************************************************General Function Area***********************************************************************/

	public String ChildFolderCreate(String stestCaseFolderName)
	{
		pathToSubfolder = null;
		try

		{
			java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
			
			if (!localMachine.getHostName().toUpperCase().contains("MS-N-")){
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());			
				subfolderCreation(stestCaseFolderName.trim().replace(" ", "_"), "_" + timeStamp);
				
			}else{
			
			     String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());				
			     subfolderCreation(stestCaseFolderName.trim().replace(" ", "_"), "_" + timeStamp);
			}

		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
	
	
		return pathToSubfolder;
	}
	/*******************************************************************************General Function Area***********************************************************************/

	@SuppressWarnings("deprecation")
	public WebDriver initializeWedriver(String sdriverName, String sDefaultPath)
	{
		WebDriver driver = null;
		try

		{
			switch (sdriverName.toUpperCase())
			{
			case "CHROME":
				Runtime.getRuntime().exec("TASKKILL /F /IM msedgedriver.exe"); 
				Runtime.getRuntime().exec("TASKKILL /F /IM chrome.exe");
				Runtime.getRuntime().exec("TASKKILL /F /IM chromedriver.exe");
			 
				Thread.sleep(700);
				
				System.setProperty("webdriver.chrome.driver", sDefaultPath+"/drivers/chromedriver.exe");
				
				Thread.sleep(10000);
				ChromeOptions options = new ChromeOptions();

				DesiredCapabilities capabilities = new DesiredCapabilities ();


				  options.addArguments(
				    "--ignore-certificate-errors",
				   "--allow-running-insecure-content",
				   "--allow-insecure-localhost",
				   "--remote-allow-origins=*",
				   "--disable-notifications"
				  );

				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(capabilities);
				Thread.sleep(50);
				//driver.manage().window().maximize();
				driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 900));
				Thread.sleep(50);
				Thread.sleep(50);
				break;

			}

		}catch(Exception e)
		{
			extmngr.getTest().log(LogStatus.FAIL,e.getMessage());
			Assert.assertEquals(6, 5);
		}
		return driver;

	}

	public void navigate(WebDriver driver,ExtentTest logger, String URL) throws IOException
	{
		try{
			

			driver.get(URL);
			Thread.sleep(6000);
			driver.manage().window().maximize();
			//driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 900));
			
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			    driver.get(URL);
				driver.get(URL);			
				Thread.sleep(6000);

			    break;

			}
			driver.manage().window().maximize();
			
		}catch (Exception e){
			if (e.getMessage().toLowerCase().contains("failed to navigate")){
				Runtime.getRuntime().exec("TASKKILL /F /IM iexplore.exe");
				Runtime.getRuntime().exec("TASKKILL /F /IM IEDriverServer.exe");
				extmngr.getTest().log(LogStatus.FAIL,e.getMessage());
				
			}else{	
				Runtime.getRuntime().exec("TASKKILL /F /IM iexplore.exe");
				Runtime.getRuntime().exec("TASKKILL /F /IM IEDriverServer.exe");
				extmngr.getTest().log(LogStatus.FAIL,e.getMessage());
			}
		}
	}

	public ExtentReports initializeExtentReports() throws Exception
	{

		try {
		/* Based on the Boolean flag, the existing report has to be overwritten or a new 
		 * report must be generated.  True  is the default value, 
		 * meaning that all existing data will be overwritten.
		 */
			java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
		if (DataFunctions.sFlag.contains("false")){

			if (!localMachine.getHostName().toUpperCase().contains("MS-N-")) {
				//assumption that the person is not running from jenkins
				mngr.extent = new ExtentReports(System.getProperty("user.dir") + "//report//index.html", false, DisplayOrder.NEWEST_FIRST);
				//extent.loadConfig(new File(System.getProperty("user.dir")+"//report//extent-config.xml"));
				mngr.extent
						.addSystemInfo("Host Name", System.getProperty("user.name"))
						.addSystemInfo("Environment", "REMWASTE Assessment")
						.addSystemInfo("User Name", System.getProperty("user.name"));

				mngr.getReporter().flush();


			}

		}else {

			if (!localMachine.getHostName().toUpperCase().contains("MS-N-")) {
				//assumption that the person is not running from jenkins
				mngr.extent = new ExtentReports(System.getProperty("user.dir") + "//report//index.html", true, DisplayOrder.NEWEST_FIRST);
				mngr.extent.loadConfig(new File(System.getProperty("user.dir") + "//report//extent-config.xml"));
				mngr.extent
						.addSystemInfo("Host Name", System.getProperty("user.name"))
						.addSystemInfo("Environment", "Dev in progress")
						.addSystemInfo("User Name", System.getProperty("user.name"));
				mngr.getReporter().flush();


			}
		}
		}catch(Exception e) {
			  e.printStackTrace();
		}
		return mngr.extent;
	}


	/********************************************************************************************************************************************
	 * Extent Reporting
	 * @throws Exception 
	 ******************************************************************************************************************************************/
	public void ExtentLogPass1(WebDriver driver, String sMessagePass, Boolean Screenshot, String sDefaultPath) throws Exception
	{
		try {	
			java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
			sMessagePass = sMessagePass.trim().replace(" ", "_");
		if (Screenshot)
		{
			String fileName=takeScreenShot(driver,"ExtentLogPass"+sMessagePass , sDefaultPath + "\\");
			if (!localMachine.getHostName().toUpperCase().contains("MS-N-")){
				extmngr.getTest().log(LogStatus.PASS, sMessagePass, extmngr.getTest().addScreenCapture(fileName));
				Assert.assertEquals(6, 6);
			}else{

				extmngr.getTest().log(LogStatus.PASS, sMessagePass, extmngr.getTest().addScreenCapture(fileName.substring(fileName.indexOf("images"))));
				Assert.assertEquals(6, 6);
			}
		}
		else
		{
			extmngr.getTest().log(LogStatus.PASS,sMessagePass);
		}
		 } catch (Exception e1) {
				e1.printStackTrace();
			}
	}

	public void PageFocus(WebDriver driver, String strWindow) throws Exception {


		Thread.sleep(2000);
		Set<String> handlers = driver.getWindowHandles();
		System.out.println("Number of windows "+driver.getWindowHandles().size());
		if (driver.getWindowHandles().size() >= 1) {
			Thread.sleep(200);
			for (String handler : handlers) {
				System.out.println("handler: "+handler);
				driver.switchTo().window(handler);
				Thread.sleep(200);
				if (driver.switchTo().window(handler).getCurrentUrl().contains(strWindow)) {
					driver.switchTo().window(handler).getCurrentUrl().contains(strWindow);
					System.out.println("Set focus on pop window" + driver.getCurrentUrl());
					System.out.println("Current URL is now: " + driver.getCurrentUrl());
					break;
				} else {
					System.out.println("Current URL: " + driver.getCurrentUrl());
				}
			}
		}
	}


	/********************************************************************************************************************************************
	 * Extent Reporting
	 * @throws Exception 
	 ******************************************************************************************************************************************/

	public void ExtentLogFail1(WebDriver driver, String sMessageFail,Boolean Screenshot, String sDefaultPath) throws Exception
	{
	try { 
		java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
		sMessageFail = sMessageFail.trim().replace(" ", "_");
		if (Screenshot)
		{
			String fileName=takeScreenShot(driver,"ExtentLogFail"+sMessageFail , sDefaultPath + "\\");

			if(!localMachine.getHostName().toUpperCase().contains("MS-N-")){
				extmngr.getTest().log(LogStatus.FAIL, sMessageFail, extmngr.getTest().addScreenCapture(fileName));
				driver.close();
				Assert.assertEquals(5, 6);
				
			}else{
				extmngr.getTest().log(LogStatus.FAIL, sMessageFail,extmngr.getTest().addScreenCapture(fileName.substring(fileName.indexOf("images"))));
				driver.close();
				Assert.assertEquals(5, 6);
			}
		}
		else
		{
			extmngr.getTest().log(LogStatus.FAIL,sMessageFail);
			driver.close();
			Assert.assertEquals(5, 6);
		}	
	} catch (Exception e1) {
		e1.printStackTrace();
		Assert.assertEquals(5, 6);
	}
	}


	/*****************************************************************************
	Function Name: 	takeScreenShot

 ******************************************************************************/	

	public String takeScreenShot(WebDriver driver,String FileName,String sDefaultPath ) throws Exception {
		String fileName="Empty";
		try
		{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			fileName =sDefaultPath+FileName+timeStamp+".png";
			System.out.println("File name and path is at " + fileName);
			
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return fileName;
	}

	/*****************************************************************************
		Function Name: 	ClickObject

	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 ******************************************************************************/	
	public void ClickObject(WebDriver driver, String property, String path) throws SAXException, IOException, ParserConfigurationException
	{
		//get object properties from the xml file repository
		String[] element = xmlParser(path, property);
		switch (element[0].toUpperCase())
		{
		case "XPATH":
			driver.findElement(By.xpath(element[1])).click();	
			break;

		case "ID":
			driver.findElement(By.id(element[1])).click();
			break;

		case "NAME":
			driver.findElement(By.name(element[1])).click();	
			break;

		case "LINKTEXT":
			driver.findElement(By.linkText(element[1])).click();
			break;

		case "CSSSELECTOR":
			driver.findElement(By.cssSelector(element[1])).click();
			break;

		}


	}

	/*****************************************************************************
		Function Name: 	EnterText
		Description:	Enter text to the application using either xpath, ID, Name, linktext and CssSelector and maximum wait time
	 ******************************************************************************/	
	public void EnterText(WebDriver driver, String property, String sText,String path) throws SAXException, IOException, ParserConfigurationException
	{
		//get object properties from the xml file repository
		String[] element = xmlParser(path, property);
		switch (element[0].toUpperCase())
		{
		case "XPATH":
			driver.findElement(By.xpath(element[1])).sendKeys(sText);
			break;

		case "ID":
		
			driver.findElement(By.id(element[1])).sendKeys(sText);	
			break;

		case "NAME":
			driver.findElement(By.name(element[1])).sendKeys(sText);
			break;

		case "LINKTEXT":
			driver.findElement(By.linkText(element[1])).sendKeys(sText);	
			break;

		case "CSSSELECTOR":
			driver.findElement(By.cssSelector(element[1])).sendKeys(sText);	
			break;
		}


	}


	/*****************************************************************************
		Function Name: 	checkIfObjectIsDisplayed
	 ******************************************************************************/
	public boolean checkIfObjectIsDisplayed(WebDriver driver, String property, String path)
	{
		boolean exists = false;
		try
		{
			//get object properties from the xml file repository
			String[] element = xmlParser(path, property);
			switch (element[0].toUpperCase())
			{
			case "XPATH":
				if(driver.findElement(By.xpath(element[1])).isDisplayed() == true){
					exists=true;
				}else{
					exists=false;
				}
				break;

			case "ID":
				if(driver.findElement(By.id(element[1])).isDisplayed() == true){
					exists=true;
				}else{
					exists=false;
				}
				break;

			case "NAME":
				if(driver.findElement(By.name(element[1])).isDisplayed() == true){
					exists=true;
				}else{
					exists=false;
				}
				break;
			case "LINKTEXT":
				if(driver.findElement(By.linkText(element[1])).isDisplayed() == true){
					exists=true;
				}else{
					exists=false;
				}
				break;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			exists=false;
		}
		return exists;

	}



	/*********************************************************/

	public String[] xmlParser(String xmlPath, String tagName) throws SAXException, IOException, ParserConfigurationException {

		String[] element2 = new String[2];
		File fXmlFile = new File(xmlPath);
		DocumentBuilderFactory dbFactory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder =
				dbFactory.newDocumentBuilder();

		try{
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(tagName);


			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String element = eElement.getElementsByTagName("identifier").item(0).getTextContent();
					String element1 = eElement.getElementsByTagName("Element").item(0).getTextContent();
					element2[0] = element;
					element2[1] = element1;

				} // end if
			} // end for loop

		} catch (Exception e) {
			Assert.fail("Something went wrong: " +e.getMessage());
			System.out.print(e.getMessage());
		}	


		return element2;
	} // end function


	public String ScreenshotParentFolder(String packageName, String sDefaultPath) throws IOException
	
	{
		
	try{
		
		 
        Path pathParentDirectory = Paths.get(sDefaultPath+"\\screenshots"+"/"+"_"+packageName);
		outputFile = new File(sDefaultPath+"\\screenshots"+"/"+"_"+packageName);
		if (Files.notExists(pathParentDirectory,LinkOption.NOFOLLOW_LINKS)) 
		{
			outputFile.mkdir();
			pathToParentScreenshotfolder =outputFile.getPath();
			System.out.println(pathToParentScreenshotfolder);
		}
	}  catch (Exception e) {
		System.out.println(e.getMessage());
	}

	return pathToParentScreenshotfolder;

	}


	//This is for second time folder creation
	public String subfolderCreation(String sScenario_Name, String timestamp){
		

		try{

		outputFile = new File(pathToParentScreenshotfolder+"/"+sScenario_Name+timestamp);
		outputFile.mkdirs();
		//System.out.println(outputFile);
		pathToSubfolder = outputFile.getPath();
		//String pathToSubfolder = outputFile.getPath();
		System.out.print(pathToSubfolder);		
		
		
		}  catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return pathToSubfolder;
	}
}
	

