package stepdefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AMS_PageObjects.AMSCommonObj;
import dataprovider.ConfigFileReader;
import dataprovider.ExcelData;
import helper.AlertHelper;
import helper.ClicksAndActionsHelper;
import helper.Selenium_Actions;
import helper.WaitHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.BaseClass;

public class AMS_Steps {
	
	WebDriver driver = BaseClass.driver;
	ConfigFileReader ConfigFileReaderobj = new ConfigFileReader();
	Selenium_Actions seleniumAction = new Selenium_Actions(driver);
	ClicksAndActionsHelper actions = new ClicksAndActionsHelper(driver);
	AlertHelper alert = new AlertHelper(driver);
	WaitHelper wait = new WaitHelper(driver);
	AMSLogin login = new AMSLogin(driver);
	AMSCommonObj amsLoginObj = new AMSCommonObj(driver);
	CommonFunctions commonFunctions = new CommonFunctions();
	Map<String, String> testData;
	Map<String, String> executionData;

	String path = System.getProperty("user.dir") +ConfigFileReaderobj.getTestDataFileName();
	ExcelData MasterExceData = new ExcelData(path, "MasterExcelData", "TestCase ID");	
	
	
	@And("User update test data set id for COM_001")
	public void user_update_test_data_set_id_for_com_001() {
		testData = MasterExceData.getTestdata("COM_001");
	}
	
	@Given("^Navigate to AMS standard application and login with valid credentials$")
	public void navigate_to_ams_standard_application_and_login_with_valid_credentials() throws Throwable{
		driver.get(ConfigFileReaderobj.getAMSApplicationUrl());
		login.loginIntoAMSApplication("admin");
	}
	
	@And("^User logout the AMS standard application$")
	public void user_logout_the_ams_standard_application_and_login_with_valid_credentials() throws Throwable{
		commonFunctions.logout();
	}

	
	
	
}
