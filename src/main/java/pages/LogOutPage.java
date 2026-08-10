package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class LogOutPage extends RootPage {

	public LogOutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//div[@class='pull-right']")
	private WebElement continueBtn;
	
	@FindBy(xpath = "//ul[@class='breadcrumb']/li/a[text()='Logout']")
	private WebElement logoutPageBreadcrumb;
	
	public HomePage clickOnContinueBtn() {
		elementUtilities.clickOnElement(continueBtn);
		return new HomePage(driver);
	}
	
	public boolean logoutPageBreadcrumbIsDisplayed(){
		return elementUtilities.isElementIsDisplayed(logoutPageBreadcrumb);
	}
	
	public String getLogOutPageHeading() {
		return getCurrentPageHeading();
	}
}
