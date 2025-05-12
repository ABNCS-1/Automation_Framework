package helper;

import org.openqa.selenium.WebDriver;

public class Selenium_Actions {
   
   // WebDriver instance used by all helpers to interact with the browser
   public WebDriver driver;
   
   // Helper classes to be used for different Selenium actions
   private AlertHelper alertHelper;
   private BrowserHelper browserHelper;
   private ClicksAndActionsHelper clickAndActionsHelper;
   private GenericHelper genericHelper;
   private ScreenshotHelper screnshotHelper;
   private JavascriptHelper javascriptHelper;
   private VerificationHelper verficationHelper;
   private WaitHelper waitHelper;
   private DropDownHelper dropDownHelper;

   // Constructor to initialize Selenium_Actions class with WebDriver instance
   public Selenium_Actions(WebDriver driver) {
       this.driver = driver; // Assigning WebDriver instance to be used by all helper classes
   }

   // Returns an instance of AlertHelper to handle browser alerts
   public AlertHelper getAlertHelper() {
       alertHelper = new AlertHelper(driver); // Initialize AlertHelper with WebDriver
       return alertHelper;
   }

   // Returns an instance of BrowserHelper to handle browser-specific actions (navigation, refresh, etc.)
   public BrowserHelper getBrowserHelper() {
       browserHelper = new BrowserHelper(driver); // Initialize BrowserHelper with WebDriver
       return browserHelper;
   }

   // Returns an instance of ClicksAndActionsHelper to handle click actions and more advanced actions like double-click, right-click, etc.
   public ClicksAndActionsHelper getClickAndActionsHelper() {
       clickAndActionsHelper = new ClicksAndActionsHelper(driver); // Initialize ClicksAndActionsHelper with WebDriver
       return clickAndActionsHelper;
   }

   // Returns an instance of GenericHelper for common utility functions like checking element presence
   public GenericHelper getGenericHelper() {
       genericHelper = new GenericHelper(); // Initialize GenericHelper (No WebDriver needed)
       return genericHelper;
   }

   // Returns an instance of ScreenshotHelper to capture screenshots
   public ScreenshotHelper getScrenshotHelper() {
       screnshotHelper = new ScreenshotHelper(driver); // Initialize ScreenshotHelper with WebDriver
       return screnshotHelper;
   }

   // Returns an instance of JavascriptHelper to execute JavaScript commands within the browser
   public JavascriptHelper getJavascriptHelper() {
       javascriptHelper = new JavascriptHelper(driver); // Initialize JavascriptHelper with WebDriver
       return javascriptHelper;
   }

   // Returns an instance of VerificationHelper to verify conditions like element presence, text matching, etc.
   public VerificationHelper getVerficationHelper() {
       verficationHelper = new VerificationHelper(); // Initialize VerificationHelper (No WebDriver needed)
       return verficationHelper;
   }

   // Returns an instance of WaitHelper to handle explicit, implicit, and fluent waits
   public WaitHelper getWaitHelper() {
       waitHelper = new WaitHelper(driver); // Initialize WaitHelper with WebDriver
       return waitHelper;
   }

   // Returns an instance of DropDownHelper to handle dropdown actions (select by visible text, value, etc.)
   public DropDownHelper getDropDownHelper() {
       dropDownHelper = new DropDownHelper(driver); // Initialize DropDownHelper with WebDriver
       return dropDownHelper;
   }

   
}
