package stepdefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import AMS_PageObjects.AMSCommonObj;
import dataprovider.ConfigFileReader;
import dataprovider.ExcelData;
import helper.AlertHelper;
import helper.ClicksAndActionsHelper;
import helper.WaitHelper;

public class AMSLogin {
	WebDriver driver;

	AMSCommonObj amsLoginObjects;
	ConfigFileReader ConfigFileReaderobj = new ConfigFileReader();
	String path = System.getProperty("user.dir") +ConfigFileReaderobj.getTestDataFileName();
	String TestDataPath = System.getProperty("user.dir") + "\\TestData\\AMSTestData.xlsx";
	ExcelData AMSLoginExceldata = new ExcelData(TestDataPath, "AMS_Login", "UserName");

	Map<String, String> AMSLoginTestData = new HashMap<>();
	AlertHelper alertHelper = new AlertHelper(driver);
	WaitHelper waitHelper = new WaitHelper(driver);

	ClicksAndActionsHelper clicksAndActionHelper;

	public AMSLogin(WebDriver driver) {
		this.driver = driver;
	}
	
//	AMS Login functionality
	public void loginIntoAMSApplication(String userType) throws IOException, InterruptedException {
		AMSLoginTestData = AMSLoginExceldata.getTestdata(userType);
		amsLoginObjects = new AMSCommonObj(driver);
		clicksAndActionHelper = new ClicksAndActionsHelper(driver);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		waitHelper.waitForElementwithFluentwait(driver, amsLoginObjects.AMSLogin_UserName());
		amsLoginObjects.AMSLogin_UserName().click();
		amsLoginObjects.AMSLogin_UserName().sendKeys(AMSLoginTestData.get("UserName"));
		
		waitHelper.waitForElementwithFluentwait(driver, amsLoginObjects.AMSLogin_Password());
		amsLoginObjects.AMSLogin_Password().click();
		amsLoginObjects.AMSLogin_Password().sendKeys(AMSLoginTestData.get("Password"));
		
		waitHelper.waitForElementwithFluentwait(driver, amsLoginObjects.AMSLogin_LoginBtn());
		amsLoginObjects.AMSLogin_LoginBtn().click();
		
	}


}
