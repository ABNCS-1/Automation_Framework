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
	
	@FindBy(xpath = "//li[@title='Master']")
	private WebElement AMS_Master;
	public WebElement AMS_Master() {
		return AMS_Master;
	}
	
	@FindBy(xpath = "//li[@title='Department']")
	private WebElement AMS_Master_Department;
	public WebElement AMS_Master_Department() {
		return AMS_Master_Department;
	}
	
	
	
	// Company screen webelements
	@FindBy(xpath = "//li[@title='Company']")
	private WebElement AMS_Master_Company;
	public WebElement AMS_Master_Company() {
		return AMS_Master_Company;
	}
	
	@FindBy(xpath = "//div[@id='workingArea']")
	private WebElement AMS_Master_Company_Grid_Refresh_Btn;
	public WebElement AMS_Master_Company_Grid_Refresh_Btn() {
		return AMS_Master_Company_Grid_Refresh_Btn;
	}
	
	@FindBy(xpath = "(//div[@id='ToolBar']//div)[2]")
	private WebElement AMS_Master_Company_AddBtn;
	public WebElement AMS_Master_Company_AddBtn() {
		return AMS_Master_Company_AddBtn;
	}
	
	@FindBy(id = "CompanyCode")
	private WebElement Master_Company_Create_CompanyCode_Input;
	public WebElement Master_Company_Create_CompanyCode_Input() {
		return Master_Company_Create_CompanyCode_Input;
	}	
	
	@FindBy(id = "CompanyName")
	private WebElement Master_Company_Create_CompanyName_Input;
	public WebElement Master_Company_Create_CompanyName_Input() {
		return Master_Company_Create_CompanyName_Input;
	}
	
	@FindBy(xpath = "//button[@id='loginButton']")
	private WebElement AMS_Save_Btn;
	public WebElement AMS_Save_Btn() {
		return AMS_Save_Btn;
	}
	
	
	
}
