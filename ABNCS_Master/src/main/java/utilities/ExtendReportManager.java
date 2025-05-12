package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportManager {
    // Formatter for the current date and time
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yy_HH_mm_ss");
    // Get the current date and time
    static LocalDateTime localDateTime = LocalDateTime.now();
    // Format the current date and time into a string
    static String currentDateAndTime = localDateTime.format(dateTimeFormatter);
    // Instance of ExtentReports to generate reports
    static ExtentReports extentReport = new ExtentReports();
    // Path for the reports, created in the user's current directory
    public static String reportPath = System.getProperty("user.dir") + "\\ExtentReports\\TestReports" + " " + currentDateAndTime;

    // This method will create a folder for extent reports and set up the report
    public static ExtentReports setupReports() {
        // Create an ExtentSparkReporter instance for the report
        ExtentSparkReporter extentSparkReport = new ExtentSparkReporter(reportPath + "\\TestReport.html");
        // Log the report instance for debugging
        System.out.println("Extent report instance: " + extentSparkReport);
        // Attach the Spark reporter to the ExtentReports instance
        extentReport.attachReporter(extentSparkReport);
        return extentReport; // Return the configured ExtentReports instance
    }

    // This method will store screenshots to attach to reports
    public static String storeReportScreenshots(File sourceFile) throws IOException {
        // Specify the directory for storing screenshots
        String filePath = reportPath + "\\ScreenShots";
        // Create a destination File object for the screenshot directory
        File destinationFile = new File(filePath);
        // Copy the screenshot from the source to the destination directory
        FileUtils.copyFileToDirectory(sourceFile, destinationFile);
        // Construct the full path of the screenshot file
        String screenshotPath = filePath + "\\" + sourceFile.getName();
        return screenshotPath; // Return the path of the stored screenshot
    }
}
