package WebUI.objectPages;


import java.time.Duration;

import Functions.UtilityFunctions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {


	static UtilityFunctions utils = new UtilityFunctions();

	 public static void AppLogin(WebDriver driver, String sEmail, String sPass, String sDefaultPath){

		 try {
			 Thread.sleep(1000);
			 if (utils.checkIfObjectIsDisplayed(driver, "txtTitle", sDefaultPath + "\\Repository\\WebUI\\Login.xml")) {
				 utils.ExtentLogPass1(driver, "Pizza Time Launch successfull", true, UtilityFunctions.pathToSubfolder);
				 utils.ClickObject(driver, "mainMenu", sDefaultPath + "\\Repository\\WebUI\\Login.xml");

				 if (utils.checkIfObjectIsDisplayed(driver, "buttonlogin", sDefaultPath + "\\Repository\\WebUI\\Login.xml")) {


					 utils.EnterText(driver, "txtEmail", sEmail, sDefaultPath + "\\Repository\\WebUI\\Login.xml");
					 utils.EnterText(driver, "txtPassword", sPass, sDefaultPath + "\\Repository\\WebUI\\Login.xml");

					 utils.ClickObject(driver, "buttonlogin", sDefaultPath + "\\Repository\\WebUI\\Login.xml");
					 utils.ExtentLogPass1(driver, "Login successfull", true, UtilityFunctions.pathToSubfolder);

					 Thread.sleep(1000);

				 } else {
					 utils.ExtentLogFail1(driver, "Login button not clicked", true, UtilityFunctions.pathToSubfolder);
				 }

			 } else {
				 utils.ExtentLogFail1(driver, "Launch unsuccessfull", true, UtilityFunctions.pathToSubfolder);
			 }

		 } catch (Exception e) {
			System.out.println(e);
		 }
	 }

}
