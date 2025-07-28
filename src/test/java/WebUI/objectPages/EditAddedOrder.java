package WebUI.objectPages;


import Functions.UtilityFunctions;
import org.openqa.selenium.WebDriver;

public class EditAddedOrder {

	static UtilityFunctions utils = new UtilityFunctions();

	 public static void EditCreatedPizzaOrder(WebDriver driver, String sEmail, String sPass, String sItemToSerch, String sDefaultPath){


		 try {

			 Thread.sleep(1000);


			 //LoginPage.AppLogin(driver, sEmail, sPass, sDefaultPath);
			 utils.ClickObject(driver, "viewMore", sDefaultPath + "\\Repository\\WebUI\\AddNewItem.xml");

			 utils.PageFocus(driver, sDefaultPath);

				 if (utils.checkIfObjectIsDisplayed(driver, "pizzaMenu", sDefaultPath + "\\Repository\\WebUI\\AddNewItem.xml")) {

					 utils.ClickObject(driver, "pizzaMenu", sDefaultPath + "\\Repository\\WebUI\\AddNewItem.xml");

					 utils.PageFocus(driver, sDefaultPath);

					 utils.ClickObject(driver, "mainMenu", sDefaultPath + "\\Repository\\WebUI\\Login.xml");
                     utils.EnterText(driver, "txtEmail", sEmail, sDefaultPath + "\\Repository\\WebUI\\Login.xml");
                     utils.EnterText(driver, "txtPassword", sPass, sDefaultPath + "\\Repository\\WebUI\\Login.xml");

					 utils.ClickObject(driver, "buttonlogin", sDefaultPath + "\\Repository\\WebUI\\Login.xml");

					 utils.PageFocus(driver, sDefaultPath);

					 utils.ClickObject(driver, "btnCart", sDefaultPath + "\\Repository\\WebUI\\EditExistingOrder.xml");

					 utils.PageFocus(driver, sDefaultPath);

					 utils.ClickObject(driver, "btnAddMore", sDefaultPath + "\\Repository\\WebUI\\EditExistingOrder.xml");

					utils.ClickObject(driver, "btnRemove", sDefaultPath + "\\Repository\\WebUI\\EditExistingOrder.xml");


					 utils.ClickObject(driver, "btnLogout", sDefaultPath + "\\Repository\\WebUI\\EditExistingOrder.xml");

				 }

		 } catch (Exception e) {
			 //logger.log(LogStatus.FAIL,e.getMessage());
		 }
	 }

}
