package WebUI.TestCases;



import Functions.DataFunctions;
import Functions.UtilityFunctions;
import Functions.extmngr;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import WebUI.objectPages.LoginPage;


public class LoginTest__TC001 {

	private ThreadLocal<String> testName = new ThreadLocal<>();
	static ExtentReports extent;

	static String sDefaultPath;


	static DataFunctions data = new DataFunctions();
	static UtilityFunctions utils = new UtilityFunctions();

	ExtentTest logger;

	@BeforeClass
	public void setUpBeforeClass(ITestContext context) throws Exception {
		context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(1);
		context.getCurrentXmlTest().addParameter("excelsheet", "Login");
		context.getCurrentXmlTest().addParameter("excelWorkbook", "LoginData");

		sDefaultPath = System.getProperty("user.dir");
		System.out.print(System.getProperty("user.name"));
		sDefaultPath = sDefaultPath.replace("batch", "");
		data.GetEnvironmentVariables(sDefaultPath);
		extent = utils.initializeExtentReports();
		utils.FolderCreate(getClass().toString(), sDefaultPath);
		Runtime.getRuntime().exec("TASKKILL /F /IM Chromedriver.exe");
		Runtime.getRuntime().exec("TASKKILL /F /IM IEDriverServer.exe");
		Runtime.getRuntime().exec("TASKKILL /F /IM msedge.exe");

	}
	@Test(dataProvider = "DTProvider", dataProviderClass = DataFunctions.class)
	public void LoginTest(String sScenario_Name, String sStatus, String sEmail, String sPassword) throws InterruptedException {

		try {

			if (!sStatus.contains(data.getStateMarker())) {

				extmngr.startTest(sScenario_Name);
				utils.ChildFolderCreate(sScenario_Name.trim());
				testName.set(sScenario_Name);

				WebDriver driver = utils.initializeWedriver("Chrome", sDefaultPath);
				utils.navigate(driver, logger, data.getWebURL());

				LoginPage.AppLogin(driver, sEmail, sPassword, sDefaultPath);

			}

		} catch (Exception e) {
			//extmngr.getTest().log(LogStatus.FAIL, e.getMessage());
		}


	}

	@AfterMethod
	public void afterMethod() throws Exception { // 2


	}


}
