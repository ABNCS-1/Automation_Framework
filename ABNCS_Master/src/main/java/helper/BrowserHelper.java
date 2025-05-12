package helper;

import java.util.LinkedList;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class BrowserHelper {
	private WebDriver driver;
	

	// Browser Helper constructor
	// initialize web driver 
	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
		// Log.debug("BrowserHelper : " + this.driver.hashCode());
	}

	// Going back to previous page
	public void goBack() {
		driver.navigate().back();
		//ExtentTestManager.getTest().info("Going back to previous page");

	}

//Move to next page
	public void goForward() {
		driver.navigate().forward();
		//ExtentTestManager.getTest().info("Move to next page");
	}

	// Refreshing the page
	public void refresh() {
		driver.navigate().refresh();
		//ExtentTestManager.getTest().info("Refreshing the page");
	}

//Get window handles
	public Set<String> getWindowHandles() {

		return driver.getWindowHandles();
	}

	// Switched to window by index
	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());

		if (index < 0 || index > windowsId.size()) {
			throw new IllegalArgumentException("Invalid Index : " + index);
		}
		driver.switchTo().window(windowsId.get(index));
	
		//ExtentTestManager.getTest().info("Switched to window");
	}

//Switched to parent window
	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());
		driver.switchTo().window(windowsId.get(0));
	
		//ExtentTestManager.getTest().info("Switched to parent window");
	}

	public void switchToParentWithChildClose() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandles());

		for (int i = 1; i < windowsId.size(); i++) {
			
			driver.switchTo().window(windowsId.get(i));
			//ExtentTestManager.getTest().info("Switched to window");
			driver.close();
		}

		switchToParentWindow();
	}

	// switching to frame
	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		//ExtentTestManager.getTest().info("Switched to frame" + nameOrId);
	}
}
