package resources;

import java.io.IOException;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import dataprovider.ConfigFileReader;

public class BaseClass {
	
	public static WebDriver driver; // Declare a static WebDriver instance
	
	// Method to initialize the WebDriver based on the browser specified in the configuration file
	public WebDriver initializeDriver() throws IOException {
		// Create an instance of ConfigFileReader to read configuration properties
		ConfigFileReader configFileReader = new ConfigFileReader();
		
		// Get the browser name from the properties file
		String browserName = configFileReader.getBrowser();
		
		// Check if the browser is Chrome
		if (browserName.equalsIgnoreCase("chrome")) {
			// Set up ChromeOptions to customize the Chrome browser settings
			ChromeOptions options = new ChromeOptions();
			// Handle SSL and certificate errors
			options.addArguments("--ignore-ssl-errors=yes");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--remote-allow-origins=*");			
			
			// Check if headless mode is enabled
			if (configFileReader.getHeadlessMode().equalsIgnoreCase("yes")) {
				options.addArguments("--headless=new"); // Run in headless mode
			}
			
			// Initialize the ChromeDriver with the specified options
			driver = new ChromeDriver(options);
			
		// Check if the browser is Firefox
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Initialize the FirefoxDriver
			driver = new FirefoxDriver();
			FirefoxOptions options = new FirefoxOptions();
			// Handle SSL and certificate errors for Firefox
			options.addArguments("--ignore-ssl-errors=yes");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--remote-allow-origins=*");
		
		// Check if the browser is Internet Explorer
		} else if (browserName.equalsIgnoreCase("ie")) {
			// Initialize the InternetExplorerDriver
			driver = new InternetExplorerDriver();
			
		// Check if the browser is Edge
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Initialize the EdgeDriver
			driver = new EdgeDriver();
			EdgeOptions options = new EdgeOptions();
			// Handle SSL and certificate errors for Edge
			options.addArguments("--ignore-ssl-errors=yes");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--remote-allow-origins=*");
		}
		
		// Maximize the browser window for better visibility
		driver.manage().window().maximize();
		
		// Return the initialized WebDriver instance
		return driver;
	}
}
