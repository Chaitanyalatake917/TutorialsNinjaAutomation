package base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import pages.AboutUsPage;
import pages.AccountSuccessPage;
import pages.AffiliatePage;
import pages.BrandsPage;
import pages.ChangePasswordPage;
import pages.ContactUsPage;
import pages.DeliveryInformationPage;
import pages.FooterOptions;
import pages.ForgottenPasswordPage;
import pages.GiftCertificatesPage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LogOutPage;
import pages.LoginPage;
import pages.MyAccountInfoPage;
import pages.MyAccountPage;
import pages.PrivacyPolicyPage;
import pages.RegistrationPage;
import pages.ReturnsPage;
import pages.RightColumnOptions;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.SiteMapPage;
import pages.SpecialsPage;
import pages.SubscribeUnsubscribePage;
import pages.TermsConditionsPage;
import utilities.CommonUtilities;

public class Base {
	public WebDriver driver;
	public SoftAssert soft;
	public Properties prop;
	public String browserName;
	public RegistrationPage registrationPage;
	public AccountSuccessPage accountSuccessPage;
	public MyAccountPage myAccountPage;
	public SubscribeUnsubscribePage subscribeUnsubscribePage;
	public HeaderOptions headerOptions;
	public LoginPage loginPage;
	public RightColumnOptions rightColumnOptions;
	public MyAccountInfoPage myAccountInfoPage;
	public ContactUsPage contactUsPage;
	public ShoppingCartPage shoppingCartPage;
	public HomePage homePage;
	public SearchPage searchPage;
	public ForgottenPasswordPage forgottenPasswordPage;
	public FooterOptions footerOptions;
	public AboutUsPage aboutUsPage;
	public PrivacyPolicyPage privacyPolicyPage;
	public DeliveryInformationPage deliveryInformation;
	public TermsConditionsPage termsConditionsPage;
	public ReturnsPage returnsPage;
	public SiteMapPage siteMapPage;
	public BrandsPage brandsPage;
	public GiftCertificatesPage giftCertificatesPage;
	public AffiliatePage affiliatePage;
	public SpecialsPage specialsPage;
	public ChangePasswordPage changePasswordPage;
	public LogOutPage logOutpage;
	public Actions action;

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver openBrowserAndAppURL() {

		prop = CommonUtilities.loadPropertiesFile();
		browserName = prop.getProperty("browserName");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		driver.get(prop.getProperty("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;
	}
	
	public Actions getActions(WebDriver driver) {
		return new Actions(driver);
	}

	public Actions enterMultipleKeysUsingKeyboard(Actions action, Keys key, int noOfTimes) {
		
		for (int i = 0; i < noOfTimes; i++) {
			action.sendKeys(key).perform();
		}
		return action;
	}

	public Actions enterTextUsingActions(Actions action,String text) {
		action.sendKeys(text).perform();
		return action;
	}

	public String getCurrentPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void navigateBackInBrowser(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
}
