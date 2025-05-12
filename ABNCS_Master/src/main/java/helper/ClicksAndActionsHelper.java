package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClicksAndActionsHelper {
    private WebDriver driver;  // WebDriver instance for interacting with the browser

    // Constructor to initialize WebDriver
    public ClicksAndActionsHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Method to click on a WebElement using the standard click method
    public void clickOnElement(WebElement element) {
        element.click();  // Perform a click on the provided element
        // ExtentTestManager.getTest().info("Clicked on the element: " + element);
    }

   
    // Method to move the mouse pointer to a specific WebElement
    public void moveToElement(WebElement element) {
        Actions action = new Actions(driver);  // Create an instance of Actions class
        action.moveToElement(element).perform();  // Move to the specified element
        // ExtentTestManager.getTest().info("Moved to the element: " + element);
    }

    // Method to hover over an element and click on another element
    public void clickUsingActionClass(WebElement hoveringElement, WebElement clickingElement) {
        Actions action = new Actions(driver);  // Create an instance of Actions class
        action.moveToElement(hoveringElement).perform();  // Hover over the first element
        action.moveToElement(clickingElement).click().build().perform();  // Move to and click the second element
        // ExtentTestManager.getTest().info("Hovered over element: " + hoveringElement + " and clicked on: " + clickingElement);
    }

    // Method to perform a right-click (context click) on a WebElement
    public void rightClick(WebElement element) {
        Actions action = new Actions(driver);  // Create an instance of Actions class
        action.contextClick(element).perform();  // Perform a right-click on the specified element
        // ExtentTestManager.getTest().info("Right clicked on the element: " + element);
    }

    // Method to perform a double-click on a WebElement
    public void doubleClick(WebElement element) {
        Actions action = new Actions(driver);  // Create an instance of Actions class
        action.doubleClick(element).perform();  // Perform a double-click on the specified element
        // ExtentTestManager.getTest().info("Double clicked on the element: " + element);
    }

    // Method to drag a WebElement and drop it on another WebElement
    public void dragAndDrop(WebElement dragElement, WebElement dropElement) {
        Actions actions = new Actions(driver);  // Create an instance of Actions class
        actions.dragAndDrop(dragElement, dropElement).perform();  // Drag the first element and drop it on the second element
        // ExtentTestManager.getTest().info("Dragged and dropped from: " + dragElement + " to: " + dropElement);
    }

    // Method to scroll to a specific WebElement
    public void scrollToElement(WebElement element) {
        Actions action = new Actions(driver);  // Create an instance of Actions class
        action.moveToElement(element).perform();  // Move to the specified element
        // ExtentTestManager.getTest().info("Scrolled to the element: " + element);
    }
}
