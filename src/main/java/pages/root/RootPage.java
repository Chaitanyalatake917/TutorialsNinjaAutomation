package pages.root;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.AboutUsPage;
import pages.AccountSuccessPage;
import pages.DeliveryInformationPage;
import pages.FooterOptions;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.PrivacyPolicyPage;
import pages.RegistrationPage;
import pages.RightColumnOptions;
import utilities.ElementUtilities;

public class RootPage {
	protected WebDriver driver;
	protected ElementUtilities elementUtilities;

	public RootPage(WebDriver driver) {
		this.driver = driver;
		this.elementUtilities=new ElementUtilities(driver);
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	private By pageHeading=By.xpath("//div[@id='content']/h1");

	@FindBy(xpath = "//ul[@class='breadcrumb']/li/a[contains(@href,'home')]")
	private WebElement homeBreadcrumb;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li/a[contains(@href,'account')]")
	private WebElement accountBreadcrumb;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li/a[text()='Register']")
	private WebElement registerBreadcrumb;
	
	@FindBy(xpath = "//ul[@class='breadcrumb']/li/a[text()='Login']")
	private WebElement loginBreadcrumb;

	private By pageLevelWarningLocator=By.xpath("(//ul[@class='breadcrumb']/following-sibling::div)[1]");

	public String pageLevelWarning() {
		return elementUtilities.getTextOfElement(pageLevelWarningLocator);
	}

	public String getCurrentPageHeading() {
		return elementUtilities.getTextOfElement(pageHeading);
	}

	public HomePage selectHomeBreadcrumb() {
		elementUtilities.clickOnElement(homeBreadcrumb);
		return new HomePage(driver);
	}

	public LoginPage selectAccountBreadcrumb() {
		elementUtilities.clickOnElement(accountBreadcrumb);
		return new LoginPage(driver);
	}

	public RegistrationPage selectRegisterBreadcrumb() {
		elementUtilities.clickOnElement(registerBreadcrumb);
		return new RegistrationPage(driver);
	}
	
	public LoginPage selectLoginBreadcrumb() {
		elementUtilities.clickOnElement(loginBreadcrumb);
		return new LoginPage(driver);
	}
	
	public boolean loginBreadcrumbIsDisplayed() {
		return elementUtilities.isElementIsDisplayed(loginBreadcrumb);
	}
	
	public HeaderOptions getHeaderOptions(){
		return new HeaderOptions(driver);
	}

	public RightColumnOptions getRightColumnOptions() {
		return new RightColumnOptions(driver);
	}
	
	public AccountSuccessPage getAccountSuccessPage() {
		return new AccountSuccessPage(driver);
	}
	
	public MyAccountPage getMyAccountPage() {
		return new MyAccountPage(driver);
	}
	public RegistrationPage getRegistrationPage() {
		return new RegistrationPage(driver);
	}
	
	public FooterOptions getFooterOptions() {
		return new FooterOptions(driver);
	}
	
	public AboutUsPage getAboutUsPage() {
		return new AboutUsPage(driver);
	}
	
	public DeliveryInformationPage getDeliveryInformationPage(){
		return new DeliveryInformationPage(driver);
	}
	
	public PrivacyPolicyPage getPrivacyPolicyPage() {
		return new PrivacyPolicyPage(driver);
	}
}
