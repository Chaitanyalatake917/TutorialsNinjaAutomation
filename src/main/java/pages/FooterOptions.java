package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class FooterOptions extends RootPage {

	public FooterOptions(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//footer//a[text()='About Us']")
	private WebElement aboutUs;

	@FindBy(xpath = "//footer//a[text()='Delivery Information']")
	private WebElement deliveryInformation;

	@FindBy(xpath = "//footer//a[text()='Privacy Policy']")
	private WebElement privacyPolicy;

	@FindBy(xpath = "//footer//a[text()='Terms & Conditions']")
	private WebElement termsConditions;

	@FindBy(xpath = "//footer//a[text()='Contact Us']")
	private WebElement contactUs;

	@FindBy(xpath = "//footer//a[text()='Returns']")
	private WebElement returns;

	@FindBy(xpath = "//footer//a[text()='Site Map']")
	private WebElement siteMap;

	@FindBy(xpath = "//footer//a[text()='Brands']")
	private WebElement brands;

	@FindBy(xpath = "//footer//a[text()='Gift Certificates']")
	private WebElement giftCertificates;

	@FindBy(xpath="//footer//a[text()='Affiliate']")
	private WebElement affiliate;
	
	@FindBy(xpath="//footer//a[text()='Specials']")
	private WebElement specials;
	
	@FindBy(xpath="//footer//a[text()='My Account']")
	private WebElement myAccount;
	
	@FindBy(xpath="//footer//a[text()='Order History']")
	private WebElement orderHistory;
	
	@FindBy(xpath="//footer//a[text()='Wish List']")
	private WebElement wishList;
	
	@FindBy(xpath="//footer//a[text()='Newsletter']")
	private WebElement newsletter;
	
	public LoginPage selectNewsletter() {
		elementUtilities.clickOnElement(newsletter);
		return new LoginPage(driver);
	}
	public LoginPage selectWishList() {
		elementUtilities.clickOnElement(wishList);
		return new LoginPage(driver);
	}
	public LoginPage selectOrderHistory() {
		elementUtilities.clickOnElement(orderHistory);
		return new LoginPage(driver);
	}
	public LoginPage selectMyAccount() {
		elementUtilities.clickOnElement(myAccount);
		return new LoginPage(driver);
	}
	
	public SpecialsPage selectSpecials() {
		elementUtilities.clickOnElement(specials);
		return new SpecialsPage(driver);
	}
	
	public AffiliatePage selectAffiliate() {
		elementUtilities.clickOnElement(affiliate);
		return new AffiliatePage(driver);
	}
	public GiftCertificatesPage selectGiftCertificates() {
		elementUtilities.clickOnElement(giftCertificates);
		return new GiftCertificatesPage(driver);
	}

	public BrandsPage selectBrands() {
		elementUtilities.clickOnElement(brands);
		return new BrandsPage(driver);
	}

	public SiteMapPage selectSiteMap() {
		elementUtilities.clickOnElement(siteMap);
		return new SiteMapPage(driver);
	}

	public ContactUsPage selectContactUs() {
		elementUtilities.clickOnElement(contactUs);
		return new ContactUsPage(driver);
	}

	public ReturnsPage selectReturns() {
		elementUtilities.clickOnElement(returns);
		return new ReturnsPage(driver);
	}

	public TermsConditionsPage selectTermsConditions() {
		elementUtilities.clickOnElement(termsConditions);
		return new TermsConditionsPage(driver);
	}

	public PrivacyPolicyPage selectPrivacyPolicy() {
		elementUtilities.clickOnElement(privacyPolicy);
		return new PrivacyPolicyPage(driver);
	}

	public AboutUsPage selectAboutUs() {
		elementUtilities.clickOnElement(aboutUs);
		return new AboutUsPage(driver);
	}

	public DeliveryInformationPage selectDeliveryInformation() {
		elementUtilities.clickOnElement(deliveryInformation);
		return new DeliveryInformationPage(driver);
	}
}
