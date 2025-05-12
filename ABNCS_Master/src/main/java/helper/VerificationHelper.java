package helper;

import org.openqa.selenium.WebElement;

public class VerificationHelper {

    /**
     * Verifies if the WebElement is displayed on the page.
     * 
     * This is a synchronized method to ensure thread safety when multiple threads 
     * are accessing this method. It returns true if the element is displayed, otherwise false.
     *
     * @param element - WebElement to check for visibility.
     * @return boolean - true if element is displayed, false otherwise.
     */
    public static synchronized boolean verifyElementPresent(WebElement element) {
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();  // Attempt to check if the element is displayed.
        } catch (Exception ex) {
            // Exception is caught if element is not found or not interactable, isDisplayed remains false.
        }
        return isDisplayed;  // Return the visibility status.
    }

    /**
     * Verifies if the WebElement is *not* displayed on the page.
     * 
     * This method returns true if the element is NOT displayed, or if any exception occurs (element not found).
     * This is useful when you expect an element not to be present on the page.
     *
     * @param element - WebElement to check for invisibility.
     * @return boolean - true if element is not displayed or not present, false otherwise.
     */
    public static synchronized boolean verifyElementNotPresent(WebElement element) {
        boolean isNotDisplayed = false;
        try {
            element.isDisplayed();  // Attempt to check if element is displayed.
        } catch (Exception ex) {
            isNotDisplayed = true;  // If an exception occurs, assume the element is not present.
        }
        return isNotDisplayed;  // Return the result.
    }

    /**
     * Verifies if the actual text of the WebElement matches the expected text.
     * 
     * Compares the text content of a WebElement with the expected string. If they match, 
     * it returns true, otherwise it returns false.
     *
     * @param element - WebElement from which to retrieve the text.
     * @param expectedText - The expected text to compare.
     * @return boolean - true if the actual text matches the expected text, false otherwise.
     */
    public static synchronized boolean verifyTextEquals(WebElement element, String expectedText) {
        boolean flag = false;
        try {
            String actualText = element.getText();  // Retrieve the text of the WebElement.
            if (actualText.equals(expectedText)) {  // Compare actual text with expected text.
                flag = true;  // If they match, set flag to true.
            }
        } catch (Exception ex) {
            // Exception handling; if something goes wrong, flag remains false.
        }
        return flag;  // Return the result of the comparison.
    }
}
