
package stepdefinitions;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import dataprovider.ConfigFileReader;
import helper.ScreenshotHelper;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import resources.BaseClass;
import resources.ExcelTest;
import utilities.ExtendReportManager;
import utilities.ExtentTestManager;

public class HooksClass extends BaseClass {
	WebDriver driver;
	CommonFunctions commonFunctions = new CommonFunctions();
	ConfigFileReader configFileReader = new ConfigFileReader();
	String path = System.getProperty("user.dir") + configFileReader.getTestDataFileName();
	Map<String, String> testExecutionData;
	ExcelTest excelTest = new ExcelTest(path, "TestExecution", "TestCaseID");
	List<String> testCaseTagsFromExcel = excelTest.getTestCaseTagsfromExcel();
	ScreenshotHelper screenshotHelper;

	int i = 1;
	int testLine;
	String failedStep = "";
	boolean flag = false;
	List<Step> stepOfScenario;
	List<String> tags = new ArrayList<>();

	@Before
	public void browserSetup(Scenario scenario) throws Throwable {
		Collection<String> sourceTagNames = scenario.getSourceTagNames();
		tags.add(sourceTagNames.toString().replaceAll("[^a-zA-Z0-9_]", ""));
		driver = initializeDriver();
		System.out.println("Driver Initiated");
		String name = scenario.getName();
		System.out.println("Scenario : **" + name + "** Started executing");
		// ExtentTestManager.startTest(name);
		ExtentTestManager.createTest(scenario.getName());
		stepOfScenario = ExtentTestManager.getStepOfScenario(scenario);
	}

	
	@AfterStep
	public void addScreenshot(Scenario scenario) throws IOException {
		
		screenshotHelper = new ScreenshotHelper(driver);
		System.out.println("Screen shot got added");
		
		java.io.File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
		scenario.attach(fileContent, "image/png", "screenshot");
		System.out.println("I value " + i);
		i = i + 1;
		testLine = testLine + 1;
		System.out.println("Test Line " + testLine);
		File extentReportScreenshot = screenshotHelper.takeScreenshot();
		String base64Image = screenshotHelper.takeScreenshotWithBase64Image();
		for (Step step : stepOfScenario) {
			if (scenario.isFailed()) {
				failedStep = step.getText();
				ExtentTestManager.extentsLogs(Status.FAIL, failedStep);
				ExtendReportManager.storeReportScreenshots(extentReportScreenshot);
				ExtentTestManager.addScreenshots(base64Image);
				 System.out.println("log exception hooks");
			} else {
				failedStep = step.getText();
				ExtentTestManager.extentsLogs(Status.PASS, failedStep);
				ExtendReportManager.storeReportScreenshots(extentReportScreenshot);
				ExtentTestManager.addScreenshots(base64Image);
			}

			stepOfScenario.remove(step);
			break;
		}

	}

	
	@After
	public void TearDown(Scenario scenario) throws IOException, ParseException, SQLException {
		String name = scenario.getName();
		System.out.println("Scenario : **" + name + "** Stopped executing");		
		ExtentTestManager.flushReport();
		System.out.println("Browser closed");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
