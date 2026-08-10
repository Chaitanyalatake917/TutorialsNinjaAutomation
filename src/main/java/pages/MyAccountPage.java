package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class MyAccountPage extends RootPage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(linkText="Edit your account information")
	private WebElement editAcInfoLink;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Account' and @href]")
	private WebElement myAcBreadcrumb;
	
	@FindBy(linkText="Subscribe / unsubscribe to newsletter")
	private WebElement subscribeUnsubscribeBtn;
	
	@FindBy(linkText="Change your password")
	private WebElement changePasswordLink;
	
	public ChangePasswordPage clickOnChangeYourPassword() {
		elementUtilities.clickOnElement(changePasswordLink);
		return new ChangePasswordPage(driver);
	}
	
	public boolean editAcInfoLinkIsDisplayed() {
		return elementUtilities.isElementIsDisplayed(editAcInfoLink);
	}
	
	public boolean myAcBreadcrumbIsDisplayed() {
		return elementUtilities.isElementIsDisplayed(myAcBreadcrumb);
	}
	
	public boolean subscribeUnsubscribeBtnIsDisplayed() {
		return elementUtilities.isElementIsDisplayed(subscribeUnsubscribeBtn);
	}
	
	public SubscribeUnsubscribePage clickOnSubscribeUnsubscribeBtn() {
		elementUtilities.clickOnElement(subscribeUnsubscribeBtn);
		return new SubscribeUnsubscribePage(driver);
	}
	
	public MyAccountInfoPage clickEditAcInfoLink(){
		elementUtilities.clickOnElement(editAcInfoLink);
		return new MyAccountInfoPage(driver);
	}

}
