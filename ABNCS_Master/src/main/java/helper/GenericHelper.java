package helper;

import org.openqa.selenium.WebElement;

public class GenericHelper {

    /**
     * Reads and returns the text value from the provided WebElement.
     * Checks if the element is not null and is displayed before reading its value.
     * 
     * @param element - The WebElement from which the text is to be read.
     * @return - The text content of the WebElement. Returns null if the element is null or not displayed.
     */
    public String readValueFromElement(WebElement element) {
        // Check if the element is null
        if (null == element) {
            // Log information about the element being null (commented out for now)
            // ExtentTestManager.getTest().info("Element value is null");
            return null;
        }

        boolean displayed = false;
        try {
            // Check if the element is displayed
            displayed = isDisplayed(element);
        } catch (Exception e) {
            // In case of an exception, return null
            return null;
        }

        // If the element is not displayed, return null
        if (!displayed) {
            // ExtentTestManager.getTest().info("Element not displayed");
            return null;
        }

        // Get the text content of the element
        String text = element.getText();
        
        // ExtentTestManager.getTest().info("Element value is " + text);
        return text; // Return the text value
    }

    /**
     * Reads and returns the value from a specific attribute (in this case, the "value" attribute) of an input WebElement.
     * Checks if the element is not null and is displayed before reading its value.
     * 
     * @param element - The WebElement (input field) from which the "value" attribute is to be read.
     * @return - The value of the "value" attribute of the WebElement. Returns null if the element is null or not displayed.
     */
    public String readValueFromInput(WebElement element) {
        // Check if the element is null
        if (null == element) {
            // ExtentTestManager.getTest().info("Element value is null");
            return null;
        }

        // Check if the element is displayed
        if (!isDisplayed(element)) {
            // ExtentTestManager.getTest().info("Element value is null");
            return null;
        }

        // Get the value of the "value" attribute
        String value = element.getAttribute("value");

        // ExtentTestManager.getTest().info("Attribute  value is " + value);
        return value; // Return the value
    }

    /**
     * Checks whether the provided WebElement is displayed on the page.
     * 
     * @param element - The WebElement to be checked.
     * @return - Returns true if the element is displayed, false otherwise.
     */
    public boolean isDisplayed(WebElement element) {
        try {
            // Try to check if the element is displayed
            element.isDisplayed();
            // ExtentTestManager.getTest().info("Element displayed");
            return true; // Return true if no exception is thrown
        } catch (Exception e) {
            // ExtentTestManager.getTest().info("Element not displayed" + e.fillInStackTrace().toString());
            return false; // Return false if an exception is thrown
        }
    }

    /**
     * Checks whether the provided WebElement is not displayed on the page.
     * 
     * @param element - The WebElement to be checked.
     * @return - Returns true if the element is not displayed, false otherwise.
     */
    protected boolean isNotDisplayed(WebElement element) {
        try {
            // Try to check if the element is displayed
            element.isDisplayed();
            // ExtentTestManager.getTest().info("Element not displayed");
            return false; // If no exception, it means the element is displayed, so return false
        } catch (Exception e) {
            // ExtentTestManager.getTest().info("Element  displayed" + e.fillInStackTrace().toString());
            return true; // If an exception is thrown, the element is not displayed, so return true
        }
    }
}
