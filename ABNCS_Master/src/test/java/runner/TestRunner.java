package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Configure Cucumber options for running tests
@CucumberOptions(features = "src/test/java/features/AMSLogin.feature", // Path to the feature file
                 glue = "stepdefinitions", // Package for step definitions
                 // monochrome=false, // Uncomment to make step definitions colorized
                 plugin = {
                     "pretty", // Pretty print the console output
                     "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", // For reporting
                     "rerun:ReRunScenarios/FailedReRun.txt", // File to log failed scenarios for re-execution
                     "listeners.MyTestListeners" // Custom test listeners
                 },
//                  dryRun = true // Uncomment to check for compilation errors without executing tests
                 tags = "@COM" // Tag to filter scenarios to run
)
public class TestRunner extends AbstractTestNGCucumberTests {
  
}
