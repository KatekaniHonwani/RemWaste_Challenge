package Functions;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

import java.io.FileReader;

import java.io.IOException;

import java.net.UnknownHostException;

import java.text.SimpleDateFormat;
import java.util.Calendar;



import org.apache.poi.ss.usermodel.Sheet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.relevantcodes.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataFunctions {

	private String sWebURL;
	private String sWebPassword;
	private String sWebUsername;
	private String sStateMarker;
	private String sReportTag;
	public static String sFlag;
	private String sDataType;
	public String sReportName;
	public static String sPayloadFilePath;
	private String sReporthead;


	Sheet sheet;
	ExtentTest logger;
	static String sDefaultPath;
	static ExtentReports extent;

	static DataFunctions data = new DataFunctions();

	//  Leaving the getters declared already to chose one approach as this violates
	public String getWebURL() {
		return sWebURL;
	}

	public String getStateMarker() {
		return sStateMarker;
	}

	public String getReportTag() {
		return sReportTag;
	}

	public String getReportName() {
		return sReportName;
	}

	public String getWebPassword(String sParentClassName) {
		if (sParentClassName.toUpperCase().contains("POS_SALES")) {
			sWebPassword = "Auto2@2026";
		} else {
			// remove password must be the same
			if (sWebUsername.contains("BSIM4")) {
				sWebPassword = "Auto2@2026";
			} else {
				sWebPassword = "Auto2@2026";
			}
		}

		return sWebPassword;
	}

	public String getWebUserName(String sParentClassName) throws UnknownHostException {
		System.out.println(System.getProperty("user.name"));
		switch (System.getProperty("user.name").toUpperCase()) {

		case "BSEBATE":

			if (sParentClassName.toUpperCase().contains("POS_SALES")) {

				sWebUsername = "Cstore";

			} else {
				java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
				System.out.println("Hostname of local machine: " + localMachine.getHostName());
				if (localMachine.getHostName().toUpperCase().contains("PC-WFC-IT16803")) {

					sWebUsername = "BSIM9";
				} else {
					if (localMachine.getHostName().toUpperCase().contains("PC-WFC-IT16793")) {
						sWebUsername = "BSIM9";

					} else {
						sWebUsername = "BSIM9";
					}
				}
			}

			break;

		case "ILABUSER":
			if (sParentClassName.toUpperCase().contains("POS_SALES")) {

				sWebUsername = "Cstore";

			} else {
				sWebUsername = "BSIM4";
			}
			break;

		case "BOTSHELO SEBATE":
			sWebUsername = "BSIM2";
			break;

		case "THEMBINKOSI":
			sWebUsername = "BSIM3";
			break;

		case "PRUDENCE.THWALA":
			sWebUsername = "BSIM3";
			break;

		case "PHANUEL.MATHONSI":
			sWebUsername = "BSIM1";
			break;

		case "LUNGILE.MAGAGULA":
			sWebUsername = "BSIM4";
			break;

		case "JERRY.NKOMO":
			sWebUsername = "BSIM1";
			break;

		}
		return sWebUsername;
	}

	public String getPayLoadFilePath() {
		return sPayloadFilePath;
	}



	public String getReporthead() {
		return sReporthead;
	}

	/*****************************************************************************
	 * Function Name: GetEnvironmentVariables Description: gets environment
	 * variables from the config json file Date Created:
	 * 
	 * @param sDefaultPath
	 ******************************************************************************/
	public void GetEnvironmentVariables(String sDefaultPath) throws IOException, ParseException {
		File f1 = new File(sDefaultPath + "\\Config\\Environment.json");
		FileReader fr = fr = new FileReader(f1);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

		JSONParser parser = new JSONParser();
		try {
			//f1 =
			//fr = new FileReader(f1);
			Object obj = parser.parse(fr);
			JSONObject jsonObject = (JSONObject) obj;

			sStateMarker = (String) jsonObject.get("StateMarker");

			sReportTag = (String) "R-" + timeStamp;
			sFlag = (String) jsonObject.get("replaceReportFlag");
			sWebURL = (String) jsonObject.get("weburl");

			sDataType = (String) jsonObject.get("datatype");

			sReportName = (String) jsonObject.get("ReportName");

			// API variables

		} finally {
			try {
				fr.close();

			} catch (IOException ioe)

			{
				ioe.printStackTrace();
			}
		}

	}

	/**
	 * @throws Exception
	 * ***************************************************************************
	 *
	 *
	 *
	 *
	 ******************************************************************************/

	@DataProvider(name = "DTProvider", parallel = true)
	public static Object[][] DTProvider(ITestContext context) throws Exception{

		sDefaultPath = System.getProperty("user.dir");
		data.GetEnvironmentVariables(sDefaultPath);

		Object[][] testObjArray = DTproV.getTableArray(context.getCurrentXmlTest().getParameter("excelsheet"),context.getCurrentXmlTest().getParameter("excelWorkbook"));


		return testObjArray;
	}
}
