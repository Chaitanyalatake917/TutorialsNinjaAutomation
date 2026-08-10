package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class MyAccountInfoPage extends RootPage{
	
	public MyAccountInfoPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(id = "input-firstname")
	private WebElement firstName;

	@FindBy(id = "input-lastname")
	private WebElement lastName;

	@FindBy(id = "input-email")
	private WebElement eMail;

	@FindBy(id = "input-telephone")
	private WebElement telePhone;

	@FindBy(id = "input-password")
	private WebElement passWord;
	
	public String getFirstNameValue() {
		return elementUtilities.getDomPropertyOfElement(firstName,"value");
	}
	
	public String getLastNameValue() {
		return elementUtilities.getDomPropertyOfElement(lastName,"value");
	}

	public String getTelephoneValue() {
		return elementUtilities.getDomPropertyOfElement(telePhone,"value");
	}

	public String getPasswordValue() {
		return elementUtilities.getDomPropertyOfElement(passWord,"value");
	}

	public String getEmailValue() {
		return elementUtilities.getDomPropertyOfElement(eMail,"value");
	}
}
