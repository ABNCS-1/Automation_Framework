package stepdefinitions;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import dataprovider.ConfigFileReader;
import helper.ClicksAndActionsHelper;
import helper.WaitHelper;

public class User_Utility {
	
	ConfigFileReader configFileReader = new ConfigFileReader();
	WebDriver driver = driverInitializae();
	ClicksAndActionsHelper ClicksAndActionsHelperobj = new ClicksAndActionsHelper(driver);
	WaitHelper waitHelper = new WaitHelper(driver);
	Map<String, String> testData = new HashMap<>();
	String path = System.getProperty("user.dir") + configFileReader.getTestDataFileName();

	AMSLogin csmlogin = new AMSLogin(driver);

	public WebDriver driverInitializae() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--ignore-ssl-errors=yes");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--remote-allow-origins=*");
		// options.addArguments("--incognito");
		// run chrome browser in headless mode
		
		if (configFileReader.getHeadlessMode().equalsIgnoreCase("yes")) {
			options.addArguments("--headless=new");
		}
		
		WebDriver driver = new ChromeDriver(options);
		return driver;

	}
	
	

}
