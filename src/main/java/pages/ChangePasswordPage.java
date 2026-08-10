package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class ChangePasswordPage extends RootPage{
	
	public ChangePasswordPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(id="input-confirm")
	private WebElement confirm;
	
	@FindBy(xpath="//div[@class='pull-right']/input[@value='Continue']")
	private WebElement continueBtn;
	
	public MyAccountPage enterPasswordAndPasswordConfirmAndClickContinue(String pass,String con){
		elementUtilities.enterTextIntoElement(password, pass);
		elementUtilities.enterTextIntoElement(confirm, con);
		elementUtilities.clickOnElement(continueBtn);
		return new MyAccountPage(driver);
	}

}
