package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class SubscribeUnsubscribePage extends RootPage {
	
	SubscribeUnsubscribePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement newsletterSubscriptionHeading;

	@FindBy(xpath = "//input[@value=1 and @name='newsletter']")
	private WebElement yesNewsletterBtn;

	@FindBy(xpath = "//input[@value=0 and @name='newsletter']")
	private WebElement noNewsletterBtn;

	public boolean newsletterSubscriptionHeadingIsDisplayed() {
		return elementUtilities.isElementIsDisplayed(newsletterSubscriptionHeading);
	}

	public boolean yesNewsletterBtnIsSelected() {
		return elementUtilities.isElementIsSelected(yesNewsletterBtn);
	}

	public boolean noNewsletterBtnIsSelected() {
		return elementUtilities.isElementIsSelected(noNewsletterBtn);
	}

}
