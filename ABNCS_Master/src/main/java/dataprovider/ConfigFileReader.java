package dataprovider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath = "configs//data.properties";

	// Constructor initializes the Properties object and loads the key-value pairs from the properties file
	// The properties file is essential for configuring various application settings (URLs, timeouts, etc.)
	public ConfigFileReader() {
		BufferedReader reader;
		try {
			// Reads the properties file using BufferedReader, ensuring the file is read in a stream
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				// Loads the key-value pairs from the properties file into the Properties object
				properties.load(reader);
				// Always close the BufferedReader to avoid resource leaks
				reader.close();
			} catch (IOException e) {
				// Handles any issues while loading the properties file
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// Throws a runtime exception if the properties file is not found at the specified path
			e.printStackTrace();
			throw new RuntimeException("Configuration file not found at " + propertyFilePath);
		}
	}

	// Retrieves the implicit wait duration (in seconds) from the properties file
	// This is used in Selenium for setting up an implicit wait timeout globally for element identification
	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait); // Parses the string value to long
		else
			throw new RuntimeException("implicitlyWait not specified in the properties file.");
	}

	// Retrieves the AMS application URL from the properties file
	// This URL is needed to launch the AMS application during test execution
	public String getAMSApplicationUrl() {
		String url = properties.getProperty("AMSapplicationUrl");
		if (url != null)
			return url;
		else
			throw new RuntimeException("AMSapplicationUrl not specified in the properties file.");
	}

	
	// Retrieves the type of browser (e.g., Chrome, Firefox) from the properties file
	// Used to configure the WebDriver to launch the specified browser for automated tests
	public String getBrowser() {
		String browser = properties.getProperty("browser");
		if (browser != null)
			return browser;
		else
			throw new RuntimeException("browser not specified in the properties file.");
	}

	// Retrieves the global timeout value from the properties file
	// This timeout is used in fluent wait to determine how long to wait for certain conditions before throwing an exception
	public long getTimeOut() {
		String timeOut = properties.getProperty("timeout");
		long parseLong = Long.parseLong(timeOut);
		if (parseLong != 0)
			return parseLong; // Returns the parsed long value for the timeout
		else
			throw new RuntimeException("timeout not specified in the properties file.");
	}

	// Retrieves the polling interval for fluent wait from the properties file
	// The polling time defines how often Selenium will check for the presence of an element or condition during the wait
	public long getPollingTime() {
		String pollingTime = properties.getProperty("pollingTime");
		long parseLong = Long.parseLong(pollingTime);
		if (parseLong != 0)
			return parseLong;
		else
			throw new RuntimeException("pollingTime not specified in the properties file.");
	}

	// Retrieves the Excel runner flag status from the properties file
	// This flag determines whether the test scripts will be run from an Excel file or directly from the test cases
	public String getExcelRunnnerFlagStatus() {
		String flag = properties.getProperty("ExcelRunner");
		if (flag != null)
			return flag;
		else
			throw new RuntimeException("ExcelRunner flag not specified in the properties file.");
	}

	// Retrieves the test data file path from the properties file
	// This file contains the test data used in the automated tests for the AMS application
	public String getTestDataFileName() {
		String testDataFile = properties.getProperty("AMSapplicationExcelPath");
		if (testDataFile != null)
			return testDataFile;
		else
			throw new RuntimeException("Test data file path not specified in the properties file.");
	}

	// Retrieves the headless mode setting from the properties file
	// When headless mode is enabled, the browser runs in the background without displaying a UI, useful for running tests on servers
	public String getHeadlessMode() {
		String headless = properties.getProperty("HeadlessMode");
		if (headless != null)
			return headless;
		else
			throw new RuntimeException("HeadlessMode not specified in the properties file.");
	}
}
