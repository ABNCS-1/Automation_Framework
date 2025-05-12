package helper;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper {

    // WebDriver instance used to take screenshots
    WebDriver driver;

    // Constructor to initialize the WebDriver instance
    public ScreenshotHelper(WebDriver driver) {
        this.driver = driver; // Assign the driver passed to the constructor
    }

    /**
     * Takes a screenshot and saves it to a specified location with the test name.
     * @param testName - the name of the test to include in the screenshot filename
     * @param driver - WebDriver instance
     * @throws IOException - If an I/O error occurs during file copy
     */
    public void takeScreenshot(String testName, WebDriver driver) throws IOException {
        // Capture screenshot as a file
        File SourceFile = takeScreenshot();
        // Define destination file path using the test name
        String destinationFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + ".png";
        // Copy the source file (screenshot) to the destination path
        FileUtils.copyFile(SourceFile, new File(destinationFilePath));
    }

    /**
     * Captures and returns a screenshot as a File object.
     * @return - A File object representing the screenshot
     * @throws IOException - If an I/O error occurs during screenshot capture
     */
    public File takeScreenshot() throws IOException {
        // Capture the screenshot and store it as a File object
        File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return SourceFile; // Return the captured screenshot file
    }

    /**
     * Captures a screenshot and returns it as a Base64 string.
     * @return - A Base64-encoded string representing the screenshot
     * @throws IOException - If an I/O error occurs during screenshot capture
     */
    public String takeScreenshotWithBase64Image() throws IOException {
        // Capture screenshot as a Base64 string
        String image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return image; // Return the Base64 string
    }

    /**
     * Takes a screenshot specifically for reporting test failures.
     * The screenshot is saved to a predefined location.
     * @param testName - the name of the test for which the screenshot is taken
     * @param driver - WebDriver instance
     * @return - The path where the failure screenshot is saved
     * @throws IOException - If an I/O error occurs during file copy
     */
    public String takeScreenshotForFailureReport(String testName, WebDriver driver) throws IOException {
        // Capture the screenshot
        File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Define the destination path for failure screenshots
        String destinationFilePath = System.getProperty("user.dir") + "\\screenshots\\fail.png";
        // Copy the screenshot to the failure folder
        FileUtils.copyFile(SourceFile, new File(destinationFilePath));
        return destinationFilePath; // Return the destination path
    }

    /**
     * Takes a screenshot specifically for reporting test passes.
     * The screenshot is saved to a predefined location.
     * @param testName - the name of the test for which the screenshot is taken
     * @param driver - WebDriver instance
     * @return - The path where the passed screenshot is saved
     * @throws IOException - If an I/O error occurs during file copy
     */
    public static String takeScreenshotForPassedReport(String testName, WebDriver driver) throws IOException {
        // Capture the screenshot
        File SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Define the destination path for passed screenshots
        String destinationFilePath = System.getProperty("user.dir") + "\\screenshots\\pass.png";
        // Copy the screenshot to the passed folder
        FileUtils.copyFile(SourceFile, new File(destinationFilePath));
        return destinationFilePath; // Return the destination path
    }
}
