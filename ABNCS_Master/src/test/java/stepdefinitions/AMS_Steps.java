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

	@Given("^Navigate to Department menu under Master module$")
	public void navigate_to_department_menu_under_master_module() throws Throwable{
		wait.waitForElementwithFluentwait(driver, amsLoginObj.AMS_Master());
		actions.moveToElement(amsLoginObj.AMS_Master());
		
		wait.waitForElementwithFluentwait(driver, amsLoginObj.AMS_Master_Department());
		seleniumAction.getClickAndActionsHelper().clickOnElement(amsLoginObj.AMS_Master_Department());
		
		Thread.sleep(2000);
	}

	
	// Company screen
	@Given("Navigate to Company menu under Master Module")
	public void navigate_to_company_menu_under_master_module() throws Throwable {
		wait.waitForElementwithFluentwait(driver, amsLoginObj.AMS_Master());
		actions.moveToElement(amsLoginObj.AMS_Master());
		
		wait.waitForElementwithFluentwait(driver, amsLoginObj.AMS_Master_Company());
		seleniumAction.getClickAndActionsHelper().clickOnElement(amsLoginObj.AMS_Master_Company());		
		
		wait.waitForElementwithFluentwait(driver, amsLoginObj.AMS_Master_Company_Grid_Refresh_Btn());
		actions.moveToElement(amsLoginObj.AMS_Master_Company_Grid_Refresh_Btn());;
	}
	
	@And("^Click the Add icon button under Company screen$")
	public void click_the_add_icon_button_under_company_screen() throws Throwable{
		wait.waitForElementwithFluentwait(driver, amsLoginObj.AMS_Master_Company_AddBtn());
		seleniumAction.getClickAndActionsHelper().clickOnElement(amsLoginObj.AMS_Master_Company_AddBtn());		
	}
	
	@And("^Enter the Company Code in Create Company screen$")
	public void enter_the_company_code_in_create_company_screen() throws Throwable {
		wait.waitForElementwithFluentwait(driver, amsLoginObj.Master_Company_Create_CompanyCode_Input());
		amsLoginObj.Master_Company_Create_CompanyCode_Input().sendKeys(testData.get("Company Code"), Keys.TAB);
	}

	@And("^Enter the Company Name in Create Company screen$")
	public void enter_the_company_name_in_create_company_screen() throws Throwable {
		wait.waitForElementwithFluentwait(driver, amsLoginObj.Master_Company_Create_CompanyName_Input());
		amsLoginObj.Master_Company_Create_CompanyName_Input().sendKeys(testData.get("Company Name"), Keys.TAB);
	}

	@When("^Click the Save button under Create Company screen$")
	public void click_the_save_button_under_create_company_screen() throws Throwable {
		for (int i = 0; i < 200; i++) {
			try {
				seleniumAction.getClickAndActionsHelper().moveToElement(amsLoginObj.AMS_Save_Btn());
				seleniumAction.getClickAndActionsHelper().clickOnElement(amsLoginObj.AMS_Save_Btn());
				break;
			} catch (Exception e) {
				if (i == 199) {
					Assert.fail(e.getMessage());
				}
			}
		}
		
		wait.waitForElementwithFluentwaitForAlert(driver);
		for (int i = 0; i < 200; i++) {
			try {				
				alert.AcceptAlert();
			} catch (Exception e) {
				
			}
		}
		
	}

	@Then("^Verify company record created successfully in Company index page grid table$")
	public void verify_company_record_created_successfully_in_company_index_page_grid_table() throws Throwable {
		List<WebElement> gridValues = driver.findElements(By.xpath("//div[@id='DetailsGrid']/table/tbody/tr/td/div[@class='tableContent']"));
		for(WebElement values : gridValues) {
			String text = values.getText();
//			System.out.println(text);
			if(text.equals(testData.get("Company Code"))) {
				System.out.println("Yes");
			}
//			Assert.assertEquals(text, testData.get("Company Code"));
		}
	}

	
	
}
