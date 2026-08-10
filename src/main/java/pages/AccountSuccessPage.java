package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class AccountSuccessPage extends RootPage{

	public AccountSuccessPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(linkText="Logout")
	private WebElement logOut;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Success']")
	private WebElement successBreadcrumb;
	
	private By content=By.id("content");
	
	private By successBreadcrumbBy = By.xpath("//ul[@class='breadcrumb']//a[text()='Success']");
	
	@FindBy(xpath="//div[@class='pull-right']")
	private WebElement continueBtn;
	
	public boolean isLogoutBtnDisplayed() {
		return elementUtilities.isElementIsDisplayed(logOut);
	}

	public boolean isSuccessBreadcrumbVisible() {
		return elementUtilities.isElementIsDisplayed(successBreadcrumbBy);
	}
	
	public String getContent() {
		return elementUtilities.getTextOfElement(content);
	}
	
	public MyAccountPage clickCountinueBtn() {
		elementUtilities.clickOnElement(continueBtn);
		return new MyAccountPage(driver);
	}
}
