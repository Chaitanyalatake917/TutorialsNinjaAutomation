package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class ForgottenPasswordPage extends RootPage{
	
	ForgottenPasswordPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']/li/a[@href and text()='Forgotten Password']")
	private WebElement forgottenPasswordBreadcrumb;
	
	public boolean isForgottenPasswordBreadcrumbIsDisplayed() {
		return elementUtilities.isElementIsDisplayed(forgottenPasswordBreadcrumb);
	}
}
