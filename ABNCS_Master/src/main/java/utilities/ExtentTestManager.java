package utilities;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.core.gherkin.Pickle;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.TestCase;

public class ExtentTestManager {
    // Static instance of ExtentTest to log test details
    public static ExtentTest extentTest;

    // This method creates a new test entry in the Extent report
    @SuppressWarnings("deprecation")
    public static void createTest(String testName) {
        // Initialize the ExtentTest instance using the test name
        extentTest = ExtendReportManager.setupReports().createTest(testName);
    }

    // Log the pass or fail status in reports
    public static void extentsLogs(Status status, String message) {
        // Log based on the status provided
        if (status.toLower().toString().equals("pass")) {
            extentTest.log(Status.PASS, message);
        }
        if (status.toLower().toString().equals("info")) {
            extentTest.log(Status.INFO, message);
        }
        if (status.toLower().toString().equals("fail")) {
            extentTest.log(Status.FAIL, message);
        }
        if (status.toLower().toString().equals("warning")) {
            extentTest.log(Status.WARNING, message);
        }
    }

    // Method to add a screenshot from a given file
    public static void addScreenshots(File screenshotFile) {
        
        // System.out.println("Screen shot path in test attachment: " + screenshotFile.getAbsolutePath());
        // Add the screenshot to the report
        extentTest.addScreenCaptureFromPath(screenshotFile.getAbsolutePath());
    }

    // Method to add a screenshot from a base64 encoded string
    public static void addScreenshots(String base64Image) {
        // Add the screenshot to the report using base64 format
        extentTest.addScreenCaptureFromBase64String(base64Image);
    }

    // This method flushes the report at the end of the test run
    public static void flushReport() {
        // Write all logged information to the report file
        ExtendReportManager.extentReport.flush();
    }

    // List to hold steps of the current scenario
    static List<Step> steps;

    // This method returns a list of steps in the scenario to be used in reports
    public static List<Step> getStepOfScenario(Scenario scenario) throws Throwable {
        // Access private fields using reflection to get the scenario steps
        System.out.println("Retrieving steps for scenario");
        Field delegate = scenario.getClass().getDeclaredField("delegate");
        delegate.setAccessible(true);
        TestCaseState state = (TestCaseState) delegate.get(scenario);
        Field testCaseF = state.getClass().getDeclaredField("testCase");
        testCaseF.setAccessible(true);
        TestCase testCase = (TestCase) testCaseF.get(state);
        Field pickle = testCase.getClass().getDeclaredField("pickle");
        pickle.setAccessible(true);
        Pickle pickleState = (Pickle) pickle.get(testCase);
        // Get the list of steps from the pickle state
        steps = pickleState.getSteps();
        return steps; // Return the list of steps
    }
    
 // New method to log exceptions
    public static void logException(Throwable throwable) {
        extentTest.log(Status.FAIL, "Exception: " + throwable);
       // extentTest.log(Status.FAIL, throwable.getCause());
    }
    public static void logException(String Message) {
        extentTest.log(Status.FAIL, "Exception: " + Message);
       // extentTest.log(Status.FAIL, throwable.getCause());
    }

}
