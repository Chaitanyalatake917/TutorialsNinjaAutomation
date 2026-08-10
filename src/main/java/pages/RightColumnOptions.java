package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class RightColumnOptions extends RootPage {

	public RightColumnOptions(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//a[@class='list-group-item' and text()='Register']")
	private WebElement registerBtn;

	@FindBy(xpath="//a[@class='list-group-item' and text()='Forgotten Password']")
	private WebElement forgotPasswordBtn;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='My Account']")
	private WebElement myAccountBtn;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='Address Book']")
	private WebElement addressBookBtn;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='Wish List']")
	private WebElement wishListBtn;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='Order History']")
	private WebElement orderHistoryBtn;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='Downloads']")
	private WebElement downloadsBtn;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='Recurring payments']")
	private WebElement recurringPaymentsBtn;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='Reward Points']")
	private WebElement rewardPointsBtn;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='Returns']")
	private WebElement returnsBtn;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='Transactions']")
	private WebElement transactionsBtn;
	
	@FindBy(xpath="//a[@class='list-group-item' and text()='Newsletter']")
	private WebElement newsletterBtn;
	
	@FindBy(linkText = "Logout")
	private WebElement logoutBtn;
	
	@FindBy(linkText = "Login")
	private WebElement loginBtn;

	public RegistrationPage clickOnRegisterBtn() {
		registerBtn.click();
		return new RegistrationPage(driver);
	}
	
	public boolean logoutBtnIsDisplayed() {
		return elementUtilities.isElementIsDisplayed(logoutBtn) ;
	}
	
	public LoginPage clickOnLoginBtn() {
		elementUtilities.clickOnElement(loginBtn); 
		return new LoginPage(driver);
	}
	
	public ForgottenPasswordPage clickOnForgottenPasswordBtn() {
		forgotPasswordBtn.click();
		return new ForgottenPasswordPage(driver);
	}
	
	public LoginPage clickOnMyAccountBtn() {
		elementUtilities.clickOnElement(myAccountBtn);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnAddressBookBtn() {
		elementUtilities.clickOnElement(addressBookBtn);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnWishListBtn() {
		elementUtilities.clickOnElement(wishListBtn);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnOrderHistoryBtn() {
		elementUtilities.clickOnElement(orderHistoryBtn);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnDownloadsBtn() {
		elementUtilities.clickOnElement(downloadsBtn);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRecurringPaymentsBtn() {
		elementUtilities.clickOnElement(recurringPaymentsBtn);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRewardPointsBtn() {
		elementUtilities.clickOnElement(rewardPointsBtn);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnReturnsBtn() {
		elementUtilities.clickOnElement(returnsBtn);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnTransactionsBtn() {
		elementUtilities.clickOnElement(transactionsBtn);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnNewsletterBtn() {
		elementUtilities.clickOnElement(newsletterBtn);
		return new LoginPage(driver);
	}

	public LogOutPage clickOnLogOutBtn() {
		elementUtilities.clickOnElement(logoutBtn);
		return new LogOutPage(driver);
	}
}
