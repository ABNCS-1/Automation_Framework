package helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptHelper {
    private WebDriver driver;

    // Constructor to initialize the WebDriver instance
    public JavascriptHelper(WebDriver driver) {
        this.driver = driver; // Assign the WebDriver passed to the constructor
    }

    /**
     * Executes the provided JavaScript and returns the result.
     * @param script - The JavaScript code to execute
     * @return - The result of the JavaScript execution (can be of any type)
     */
    public Object executeScript(String script) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        return exe.executeScript(script); // Executes the provided script
    }

    /**
     * Executes the provided JavaScript with arguments and returns the result.
     * @param script - The JavaScript code to execute
     * @param args - Arguments to pass to the script
     * @return - The result of the JavaScript execution (can be of any type)
     */
    public Object executeScript(String script, Object... args) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        return exe.executeScript(script, args); // Executes script with passed arguments
    }

    /**
     * Clicks on the provided element using JavaScript.
     * Useful when the normal click method fails (e.g., due to overlaying elements).
     * @param element - The WebElement to click
     */
    public void JSEClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolls the page to the position of the given element using JavaScript.
     * @param element - The WebElement to scroll to
     */
    public void scrollToElemet(WebElement element) {
        executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
        // Scroll to the element's location using its X and Y coordinates
    }

    /**
     * Scrolls to the element and clicks on it using JavaScript.
     * @param element - The WebElement to scroll to and click
     */
    public void scrollToElemetAndClick(WebElement element) {
        scrollToElemet(element); // Scroll to the element
        element.click(); // Then click the element
    }

    /**
     * Scrolls the page until the provided element is in view using JavaScript.
     * @param element - The WebElement to scroll into view
     */
    public void scrollIntoView(WebElement element) {
        executeScript("arguments[0].scrollIntoView()", element); // Scrolls until the element is visible in the viewport
    }

    /**
     * Scrolls the page until the provided element is in view and clicks on it.
     * @param element - The WebElement to scroll into view and click
     */
    public void scrollIntoViewAndClick(WebElement element) {
        scrollIntoView(element); // Scroll into view
        element.click(); // Then click the element
    }

    /**
     * Scrolls down to the bottom of the page using JavaScript.
     */
    public void scrollDownVertically() {
        executeScript("window.scrollTo(0, document.body.scrollHeight)"); // Scrolls to the bottom of the page
    }

    /**
     * Scrolls up to the top of the page using JavaScript.
     */
    public void scrollUpVertically() {
        executeScript("window.scrollTo(0, -document.body.scrollHeight)"); // Scrolls to the top of the page
    }

    /**
     * Scrolls down the page by a specified pixel value using JavaScript.
     */
    public void scrollDownByPixel() {
        executeScript("window.scrollBy(0,1500)"); // Scrolls down by 1500 pixels
    }

    /**
     * Scrolls up the page by a specified pixel value using JavaScript.
     */
    public void scrollUpByPixel() {
        executeScript("window.scrollBy(0,-1500)"); // Scrolls up by 1500 pixels
    }

    /**
     * Zooms in the page by a specified percentage using JavaScript.
     * In this case, it zooms to 40%.
     */
    public void ZoomInBypercentage() {
        executeScript("document.body.style.zoom='40%'"); // Sets the zoom level of the page to 40%
    }

    /**
     * Resets the page zoom to 100% using JavaScript.
     */
    public void ZoomBy100percentage() {
        executeScript("document.body.style.zoom='100%'"); // Resets the zoom level of the page to 100%
    }
}
