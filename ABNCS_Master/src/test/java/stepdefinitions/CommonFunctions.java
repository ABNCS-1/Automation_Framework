package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AMS_PageObjects.AMSCommonObj;
import helper.Selenium_Actions;
import resources.BaseClass;

public class CommonFunctions {
	
	WebDriver driver = BaseClass.driver;
	Selenium_Actions seleniumActions = new Selenium_Actions(driver);
	AMSCommonObj commonWebElements = new AMSCommonObj(driver);

	// check the flag In AMS
	public void checkRequiredFlag(WebElement element) {
		seleniumActions.getWaitHelper().waitForElementwithFluentwait(driver, element);
		// check where element is checked or not
		// if checked again it will check 
		if (element.isSelected()) {
			seleniumActions.getClickAndActionsHelper().moveToElement(element);
			seleniumActions.getClickAndActionsHelper().clickUsingActionClass(element, element);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			
		}
			seleniumActions.getClickAndActionsHelper().moveToElement(element);
			seleniumActions.getClickAndActionsHelper().clickUsingActionClass(element, element);
		} else {

			seleniumActions.getClickAndActionsHelper().moveToElement(element);
			seleniumActions.getClickAndActionsHelper().clickUsingActionClass(element, element);
		}
	}
	
	// Uncheck the flag In AMS
	public void unCheckRequiredFlag(WebElement element) {
		seleniumActions.getWaitHelper().waitForElementwithFluentwait(driver, element);
		// check where element is checked or not
				// if checked it will un check the flag
		if (element.isSelected()) {
			seleniumActions.getClickAndActionsHelper().moveToElement(element);
			seleniumActions.getClickAndActionsHelper().clickUsingActionClass(element, element);

		} else {
			seleniumActions.getClickAndActionsHelper().moveToElement(element);
			seleniumActions.getClickAndActionsHelper().clickUsingActionClass(element, element);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			seleniumActions.getClickAndActionsHelper().moveToElement(element);
			seleniumActions.getClickAndActionsHelper().clickUsingActionClass(element, element);
		}
	}


// logout AMS application
public void logout() {
		for (int i = 0; i <= 300; i++) {
			try {
				seleniumActions.getWaitHelper().waitForElementwithFluentwait(driver, commonWebElements.AMS_UserName_Dropdown());
				seleniumActions.getClickAndActionsHelper().clickOnElement(commonWebElements.AMS_UserName_Dropdown());
				
				seleniumActions.getWaitHelper().waitForElementwithFluentwait(driver, commonWebElements.AMS_LogoutBtn());
				seleniumActions.getClickAndActionsHelper().moveToElement(commonWebElements.AMS_LogoutBtn());
				seleniumActions.getJavascriptHelper().JSEClick(commonWebElements.AMS_LogoutBtn());
				System.out.println("Logout button got clicked");
				break;
			} catch (Exception e) {
				if (i == 300) {
					Assert.fail(e.getMessage());
				}
			}
		}
	}

}
