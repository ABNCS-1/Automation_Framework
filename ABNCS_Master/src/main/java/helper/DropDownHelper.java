package helper;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownHelper {
 WebDriver driver;
	

	// calling constructor
//this constructor used to initialize web driver
	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
	}

	// Selecting the element from dropdown using values
	public void SelectUsingVisibleValue(WebElement element, String visibleValue) {
		Select select = new Select(element);
		select.selectByValue(visibleValue);
		
		//ExtentTestManager.getTest().info("The  value" + visibleValue + "selected from dropdown");
	}

	// Returns selected value from dropdown
	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		
		//ExtentTestManager.getTest().info("The  value " + value + "selecetd from dropdown");
		return value;

	}

	// Selecting the element from dropdown using index
	public void SelectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		
		//ExtentTestManager.getTest().info("The  index" + index + "selected from dropdown");
	}

	// Selecting the element from dropdown using text
	public void SelectUsingVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		
		//ExtentTestManager.getTest().info("The  Text" + text + "selected from dropdown");
	}

	// Returns all the available values in dropdown
	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();

		for (WebElement element : elementList) {
			
			valueList.add(element.getText());
		}
		//ExtentTestManager.getTest().info("The  values are " + valueList);
		return valueList;
	}
}
