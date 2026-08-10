package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;
import utilities.CommonUtilities;

public class LoginTest extends Base{
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver=openBrowserAndAppURL();
		headerOptions=new HeaderOptions(driver);
		headerOptions.clickOnMyAccount();
		loginPage=headerOptions.clickOnLogin();
	}
	
	@Test
	public void verifyLoginWithValidCredentials() {
		myAccountPage=loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"), prop.getProperty("validPass"));
		Assert.assertTrue(myAccountPage.myAcBreadcrumbIsDisplayed());
		Assert.assertTrue(myAccountPage.editAcInfoLinkIsDisplayed());
	}
	
	@Test
	public void verifyLoginWithInvalidCredentials() {
		loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("invalidMail0"), prop.getProperty("validPass"));
		Assert.assertEquals(loginPage.pageLevelWarning(),"Warning: No match for E-Mail Address and/or Password.");
	}
	
	@Test
	public void verifyLoginWithInvalidEmailAndCorrectPassword() {
		loginPage.enterEmailAndPasswordAndClickContinue(CommonUtilities.generateDummyMail(), prop.getProperty("validPass"));
		Assert.assertEquals(loginPage.pageLevelWarning(),"Warning: No match for E-Mail Address and/or Password.");
	}
	
	@Test
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"), prop.getProperty("incorrectPassword"));
		Assert.assertEquals(loginPage.pageLevelWarning(),"Warning: No match for E-Mail Address and/or Password.");
	}
	
	@Test
	public void verifyLoginWithoutAnyCredentials() {
		loginPage.clickLoginBtn();
		Assert.assertEquals(loginPage.pageLevelWarning(),"Warning: No match for E-Mail Address and/or Password.");
	}
	
	@Test
	public void verifyForgottenPasswordLinkIsVisibleAndWorking() {
		Assert.assertTrue(loginPage.isForgottenPasswordLinkDisplayed());
		forgottenPasswordPage=loginPage.clickForgottenPassword();
		Assert.assertEquals(getCurrentPageTitle(driver),"Forgot Your Password?");
		Assert.assertTrue(forgottenPasswordPage.isForgottenPasswordBreadcrumbIsDisplayed());
	}
	
	@Test(enabled=true)
	public void verifyLoginUsingKeyboardKeys() {
		action=enterMultipleKeysUsingKeyboard(getActions(driver),Keys.TAB,23);
		action=enterTextUsingActions(action,prop.getProperty("validMail"));
		action=enterMultipleKeysUsingKeyboard(action,Keys.TAB,1);
		action=enterTextUsingActions(action,prop.getProperty("validPass"));
		action=enterMultipleKeysUsingKeyboard(action,Keys.TAB,2);
		action=enterMultipleKeysUsingKeyboard(action,Keys.ENTER,1);
		myAccountPage=loginPage.getMyAccountPage();
		Assert.assertTrue(myAccountPage.myAcBreadcrumbIsDisplayed());
		Assert.assertTrue(myAccountPage.editAcInfoLinkIsDisplayed());
	}
	
	@Test
	public void verifyEmailIdAndPasswordFieldPlaceholder() {
		Assert.assertEquals(loginPage.getEmailIdPlaceholderValue(),"E-Mail Address");
		Assert.assertEquals(loginPage.getPasswordPlaceholderValue(),"Password");
	}
	
	@Test
	public void verifyBrowserBackAfterLogin() {
		myAccountPage=loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),prop.getProperty("validPass"));
		navigateBackInBrowser(driver);
		refreshPage(driver);
		Assert.assertTrue(myAccountPage.myAcBreadcrumbIsDisplayed());
		Assert.assertTrue(myAccountPage.editAcInfoLinkIsDisplayed());
	}
	
	@Test
	public void verifybrowserBackAfterLoggedOut() {
		myAccountPage=loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),prop.getProperty("validPass"));
		headerOptions=myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccount();
		headerOptions.clickOnLogOutBtn();
		navigateBackInBrowser(driver);
		refreshPage(driver);
		Assert.assertTrue(loginPage.isForgottenPasswordLinkDisplayed());
		Assert.assertEquals(getCurrentPageTitle(driver),"Account Login");
	}
	
	@Test
	public void verifyLoginIntoApplicationWithInactiveCredentials() {
		loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("expiredMail"),prop.getProperty("expiredMailPass"));
		Assert.assertEquals(loginPage.pageLevelWarning(),"Warning: No match for E-Mail Address and/or Password.");
	}
	
	@Test
	public void verifyPasswordIsNotVisibleInPageSource() {
		Assert.assertEquals(loginPage.getDomAttributeOfPassword(), "password");
	}
	
	@Test
	public void verifyCopyingTextOfPasswordField() {
		loginPage.enterEmailField(prop.getProperty("existingMail"));
		loginPage.enterPasswordField(prop.getProperty("validPass"));
		loginPage.copyTextOfpasswordField();
		loginPage.clickLoginBtn();
		loginPage.pasteTextIntoEmailField();
		Assert.assertNotEquals(loginPage.getDomPropertyOfEmail(), prop.getProperty("validPass"));
	}
	
	@Test
	public void verifyPasswordIsNotVisibleInPageSrc() {
		loginPage.enterPasswordField(prop.getProperty("validPass"));
		loginPage.clickLoginBtn();
		Assert.assertFalse(loginPage.getPageSrcCodeOfLoginPage(driver).contains(prop.getProperty("validPass")));
	}
	
	@Test
	public void verifyLoginAfterChangingPassword() {
		myAccountPage=loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),prop.getProperty("validPass"));
		changePasswordPage=myAccountPage.clickOnChangeYourPassword();
		myAccountPage=changePasswordPage.enterPasswordAndPasswordConfirmAndClickContinue(prop.getProperty("newValidPass"),prop.getProperty("newValidPass"));
		Assert.assertEquals(myAccountPage.pageLevelWarning(),"Success: Your password has been successfully updated.");
		rightColumnOptions=myAccountPage.getRightColumnOptions();
		logOutpage=rightColumnOptions.clickOnLogOutBtn();
		Assert.assertEquals(logOutpage.getCurrentPageHeading(),"Account Logout");
	    homePage=logOutpage.clickOnContinueBtn();
	    headerOptions=homePage.getHeaderOptions();
	    headerOptions.clickOnMyAccount();
		loginPage=headerOptions.clickOnLogin();
		myAccountPage=loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),prop.getProperty("newValidPass"));
		changePasswordPage=myAccountPage.clickOnChangeYourPassword();
		myAccountPage=changePasswordPage.enterPasswordAndPasswordConfirmAndClickContinue(prop.getProperty("validPass"),prop.getProperty("validPass"));
	}
	
	@Test
	public void verifyAllLinks() {
		headerOptions = loginPage.getHeaderOptions();
		contactUsPage = headerOptions.selectContactUs();
		Assert.assertEquals(getCurrentPageTitle(contactUsPage.getDriver()), "Contact Us");
		navigateBackInBrowser(contactUsPage.getDriver());

		headerOptions.clickOnMyAccount();
		registrationPage = headerOptions.clickOnRegister();
		Assert.assertEquals(getCurrentPageTitle(registrationPage.getDriver()), "Register Account");
		navigateBackInBrowser(registrationPage.getDriver());
		
		headerOptions.clickOnMyAccount();
		loginPage = headerOptions.clickOnLogin();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = headerOptions.selectWishlist();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		shoppingCartPage = headerOptions.selectShoppingCart1();
		Assert.assertEquals(getCurrentPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectShoppingCart2();
		Assert.assertEquals(getCurrentPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectCheckOut();
		Assert.assertEquals(getCurrentPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		homePage = headerOptions.selectLogo();
		Assert.assertEquals(getCurrentPageTitle(homePage.getDriver()), "Your Store");
		navigateBackInBrowser(homePage.getDriver());

		searchPage = headerOptions.selectSearchBtn();
		Assert.assertEquals(getCurrentPageTitle(searchPage.getDriver()), "Search");
		navigateBackInBrowser(homePage.getDriver());

		homePage = headerOptions.selectHomeBreadcrumb();
		Assert.assertEquals(getCurrentPageTitle(homePage.getDriver()), "Your Store");
		navigateBackInBrowser(homePage.getDriver());

		loginPage = headerOptions.selectAccountBreadcrumb();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = headerOptions.selectLoginBreadcrumb();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		rightColumnOptions = headerOptions.getRightColumnOptions();
		loginPage = rightColumnOptions.clickOnLoginBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		registrationPage = rightColumnOptions.clickOnRegisterBtn();
		Assert.assertEquals(getCurrentPageTitle(registrationPage.getDriver()), "Register Account");
		navigateBackInBrowser(registrationPage.getDriver());

		forgottenPasswordPage = rightColumnOptions.clickOnForgottenPasswordBtn();
		Assert.assertEquals(getCurrentPageTitle(forgottenPasswordPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgottenPasswordPage.getDriver());

		loginPage = rightColumnOptions.clickOnMyAccountBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnAddressBookBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnWishListBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnOrderHistoryBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnDownloadsBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnRecurringPaymentsBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnRewardPointsBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnReturnsBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnTransactionsBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnNewsletterBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		footerOptions = registrationPage.getFooterOptions();
		aboutUsPage = footerOptions.selectAboutUs();
		Assert.assertEquals(getCurrentPageTitle(aboutUsPage.getDriver()), "About Us");
		navigateBackInBrowser(aboutUsPage.getDriver());

		deliveryInformation = footerOptions.selectDeliveryInformation();
		Assert.assertEquals(getCurrentPageTitle(deliveryInformation.getDriver()), "Delivery Information");
		navigateBackInBrowser(deliveryInformation.getDriver());

		privacyPolicyPage = footerOptions.selectPrivacyPolicy();
		Assert.assertEquals(getCurrentPageTitle(privacyPolicyPage.getDriver()), "Privacy Policy");
		navigateBackInBrowser(privacyPolicyPage.getDriver());

		termsConditionsPage = footerOptions.selectTermsConditions();
		Assert.assertEquals(getCurrentPageTitle(termsConditionsPage.getDriver()), "Terms & Conditions");
		navigateBackInBrowser(termsConditionsPage.getDriver());

		contactUsPage = footerOptions.selectContactUs();
		Assert.assertEquals(getCurrentPageTitle(contactUsPage.getDriver()), "Contact Us");
		navigateBackInBrowser(contactUsPage.getDriver());

		returnsPage = footerOptions.selectReturns();
		Assert.assertEquals(getCurrentPageTitle(returnsPage.getDriver()), "Product Returns");
		navigateBackInBrowser(returnsPage.getDriver());

		siteMapPage = footerOptions.selectSiteMap();
		Assert.assertEquals(getCurrentPageTitle(siteMapPage.getDriver()), "Site Map");
		navigateBackInBrowser(siteMapPage.getDriver());

		brandsPage = footerOptions.selectBrands();
		Assert.assertEquals(getCurrentPageTitle(brandsPage.getDriver()), "Find Your Favorite Brand");
		navigateBackInBrowser(brandsPage.getDriver());

		giftCertificatesPage = footerOptions.selectGiftCertificates();
		Assert.assertEquals(getCurrentPageTitle(giftCertificatesPage.getDriver()), "Purchase a Gift Certificate");
		navigateBackInBrowser(giftCertificatesPage.getDriver());

		affiliatePage = footerOptions.selectAffiliate();
		Assert.assertEquals(getCurrentPageTitle(affiliatePage.getDriver()), "Affiliate Program");
		navigateBackInBrowser(affiliatePage.getDriver());

		specialsPage = footerOptions.selectSpecials();
		Assert.assertEquals(getCurrentPageTitle(specialsPage.getDriver()), "Special Offers");
		navigateBackInBrowser(specialsPage.getDriver());

		loginPage = footerOptions.selectMyAccount();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage= footerOptions.selectOrderHistory();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");

		loginPage= footerOptions.selectWishList();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
	
		loginPage= footerOptions.selectNewsletter();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
	}
	
	@Test
	public void verifyLoginWays() {
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		registrationPage=loginPage.clickContinueBtn();
		loginPage=registrationPage.clickOnLoginPageLink();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		rightColumnOptions=loginPage.getRightColumnOptions();
		rightColumnOptions.clickOnLoginBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
	}
	
	@Test
	public void verifyLoginPage() {
		Assert.assertEquals(getCurrentPageTitle(driver), "Account Login");
		Assert.assertEquals(getCurrentPageURL(driver), prop.getProperty("loginPageURL"));
		Assert.assertTrue(loginPage.loginBreadcrumbIsDisplayed());
		Assert.assertEquals(loginPage.getFirstHeading(),"New Customer");
		Assert.assertEquals(loginPage.getSecondHeading(), "Returning Customer");
	}
	
	@Test
	public void verifyUIofLoginPage() {
		if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ActualUIofLoginPagefirefoxSS.png");
			
				Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
						System.getProperty("user.dir") + "//ScreenShots//ActualUIofLoginPagefirefoxSS.png",
						System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLoginPagefirefoxSS.png"));
			
		} else if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ActualUIofLoginPageSSchrome.png");
			
				Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
						System.getProperty("user.dir") + "//ScreenShots//ActualUIofLoginPageSSchrome.png",
						System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLoginPageSSchrome.png"));
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver, System.getProperty("user.dir") + "\\Screenshots\\ActualUIofLoginPageSSedge.png");
			
				Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
						System.getProperty("user.dir") + "//ScreenShots//ActualUIofLoginPageSSedge.png",
						System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLoginPageSSedge.png"));
			
		} else if (browserName.equalsIgnoreCase("safari")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ExpectedUIofLoginPageSSsafari.png");
			
				Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
						System.getProperty("user.dir") + "//ScreenShots//ActualUIofLoginPageSSsafari.png",
						System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLoginPageSSsafari.png"));
		}
	}
	
	@Test
	public void zverifyUnsuccessfulLoginAttempts() {
		String invalidEmail=CommonUtilities.generateDummyMail();
		loginPage.enterEmailAndPasswordAndClickContinue(invalidEmail,prop.getProperty("incorrectPassword"));
		loginPage.enterEmailAndPasswordAndClickContinue(invalidEmail,prop.getProperty("incorrectPassword"));
		loginPage.enterEmailAndPasswordAndClickContinue(invalidEmail,prop.getProperty("incorrectPassword"));
		loginPage.enterEmailAndPasswordAndClickContinue(invalidEmail,prop.getProperty("incorrectPassword"));
		loginPage.enterEmailAndPasswordAndClickContinue(invalidEmail,prop.getProperty("incorrectPassword"));
		loginPage.clickLoginBtn();
		Assert.assertEquals(loginPage.pageLevelWarning(), "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.");
	}
}
