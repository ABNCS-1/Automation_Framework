package AMS_PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AMSCommonObj {
	
	WebDriver driver;
	public AMSCommonObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement for AMS login page
	@FindBy(id = "UserName")
	private WebElement AMSLogin_UserName;
	public WebElement AMSLogin_UserName() {
		return AMSLogin_UserName;
	}
	
	@FindBy(id = "Password")
	private WebElement AMSLogin_Password;
	public WebElement AMSLogin_Password() {
		return AMSLogin_Password;
	}
	
	@FindBy(id = "login")
	private WebElement AMSLogin_LoginBtn;
	public WebElement AMSLogin_LoginBtn() {
		return AMSLogin_LoginBtn;
	}
	
	@FindBy(id = "pageMainHeadingPortion")
	private WebElement AMS_Dashboard_Page;
	public WebElement AMS_Dashboard_Page() {
		return AMS_Dashboard_Page;
	}
	
	@FindBy(id = "navbarDropdownMenuLink")
	private WebElement AMS_UserName_Dropdown;
	public WebElement AMS_UserName_Dropdown() {
		return AMS_UserName_Dropdown;
	}
	
	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement AMS_LogoutBtn;
	public WebElement AMS_LogoutBtn() {
		return AMS_LogoutBtn;
	}
	
	
	
}
