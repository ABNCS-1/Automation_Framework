package helper;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import dataprovider.ConfigFileReader;

public class WaitHelper {
    private WebDriver driver;
    
    // Location of the configuration properties file
    String fileLocation = System.getProperty("user.dir") + "\\configs\\data.properties";
    FileInputStream fileInputStream;
    ConfigFileReader configFileReader = new ConfigFileReader();

    // Constructor to initialize the WebDriver instance
    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Set implicit wait for the WebDriver
    public void setImplicitWait(long timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        
    }

    // Set the page load timeout
    @SuppressWarnings("deprecation") // Suppressing deprecated warnings for backward compatibility
    public void setPageLoadTimeout(long timeout, TimeUnit unit) {
        driver.manage().timeouts().pageLoadTimeout(timeout, unit == null ? TimeUnit.SECONDS : unit);
        
    }

    // Private method to create a WebDriverWait with specific settings
    private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        // Add exception handling to ignore specific exceptions during the wait
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(ElementNotInteractableException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchFrameException.class);
        return wait;
    }

    // Wait until a specific element is visible
    public void waitForElementVisible(WebElement locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.visibilityOf(locator));
     
    }

    // Wait for a single element to be visible
    public void waitForElement(WebDriver driver, WebElement element, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
      
    }

    // Wait for an element to be clickable
    public WebElement waitForElement(WebDriver driver, long time, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait for an element with FluentWait
    public WebElement waitForElementwithFluentwait(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(configFileReader.getTimeOut())) 
                .pollingEvery(Duration.ofMillis(configFileReader.getPollingTime()))
                .ignoring(Exception.class);
        
        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
        return element1;
    }

    // Wait for an element to be visible with FluentWait
    public WebElement waitForElementToVisibleWithFluentWait(WebDriver driver, WebElement element, int timeOut, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeOut)) 
                .pollingEvery(Duration.ofMillis(pollingTime))
                .ignoring(Exception.class);
        
        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
        return element1;
    }
    
    // Wait for an alert to be present with FluentWait
    public void waitForElementwithFluentwaitForAlert(WebDriver driver) throws IOException {
        fileInputStream = new FileInputStream(fileLocation);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(configFileReader.getTimeOut()))
                .pollingEvery(Duration.ofMillis(configFileReader.getTimeOut()))
                .ignoring(Exception.class);
        
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
