package tests;

import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import helper.WaitHelper;
import resources.BaseClass;

public class BrowserLaunch extends BaseClass {

public static void main(String[] args) throws Throwable {
	WaitHelper waitHelper = new WaitHelper(driver);

	WebDriver driver = new ChromeDriver();
	ChromeOptions options = new ChromeOptions();
//	options.addArguments("window-size=1920,1080");
//	options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
//	options.setExperimentalOption("useAutomationExtension", false);
	
//	Proxy proxy = new Proxy();
//    proxy.setHttpProxy("amsdfm.devappes.com:8080");
//	options.setCapability("proxy", proxy);
	
	
	
	driver.get("http://adpfuat.devappes.com/");
//	driver.get("http://rpm.devappes.com/Account/Login?ReturnUrl=%2f");
//	driver.get("https://pmms.devappes.com/login");
	driver.manage().window().maximize();
	Thread.sleep(10000);
	
	LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.BROWSER, Level.ALL);

    options.setCapability("goog:loggingPrefs", logPrefs);
    // Retrieve and print browser console logs
    LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
    for (LogEntry entry : logs) {
        System.out.println(entry.getLevel() + " " + entry.getMessage());
    }

	
// Script for AMS login

	WebElement AMSLogin_UserName = driver.findElement(By.id("UserName"));	
	waitHelper.waitForElementwithFluentwait(driver,AMSLogin_UserName);
	AMSLogin_UserName.click();
	AMSLogin_UserName.sendKeys("admin");
	
	WebElement AMSLogin_Password = driver.findElement(By.id("Password"));
	waitHelper.waitForElementwithFluentwait(driver,AMSLogin_Password);
	AMSLogin_Password.click();
	AMSLogin_Password.sendKeys("admin@123");
	
//	Thread.sleep(10000);
	WebElement AMSLogin_LoginBtn = driver.findElement(By.id("login"));	
	AMSLogin_LoginBtn.click();
	
	WebElement AMS_Master = driver.findElement(By.xpath("//li[@title='Master']"));
	waitHelper.waitForElementwithFluentwait(driver, AMS_Master);
	AMS_Master.isDisplayed();
	
	//RPM & PMMS
//	WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
//	loginBtn.click();
	
	Thread.sleep(10000);
	driver.quit();
}
}
