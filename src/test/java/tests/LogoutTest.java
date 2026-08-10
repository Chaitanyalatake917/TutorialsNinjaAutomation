package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;
import utilities.CommonUtilities;

public class LogoutTest extends Base {

	@BeforeMethod
	public void setUp() {
		driver = openBrowserAndAppURL();
		headerOptions = new HeaderOptions(driver);
	}

	@Test
	public void verifyLoggingOutUsingMyAccountmenu() {
		headerOptions.clickOnMyAccount();
		loginPage = headerOptions.clickOnLogin();
		myAccountPage = loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),
				prop.getProperty("validPass"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccount();
		logOutpage = headerOptions.clickOnLogOutOfMyAc();
		Assert.assertEquals(getCurrentPageTitle(driver), "Account Logout");
	}

	@Test
	public void verifyLoggingOutUsingRightColumnOptions() {
		headerOptions.clickOnMyAccount();
		loginPage = headerOptions.clickOnLogin();
		myAccountPage = loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),
				prop.getProperty("validPass"));
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		logOutpage = rightColumnOptions.clickOnLogOutBtn();
		Assert.assertEquals(getCurrentPageTitle(driver), "Account Logout");
	}

	@Test
	public void verifyUserIsNotLoggedInAfterLoggedOut() {
		headerOptions.clickOnMyAccount();
		loginPage = headerOptions.clickOnLogin();
		myAccountPage = loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),
				prop.getProperty("validPass"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccount();
		logOutpage = headerOptions.clickOnLogOutOfMyAc();
		Assert.assertEquals(getCurrentPageTitle(driver), "Account Logout");
		navigateBackInBrowser(driver);
		refreshPage(driver);
		Assert.assertEquals(loginPage.getFirstHeading(), "New Customer");
		Assert.assertEquals(loginPage.getSecondHeading(), "Returning Customer");
	}

	@Test
	public void verifyLogOutOptionNotVisibleInHeaderOptionsWithoutLoggedIn() {
		headerOptions.clickOnMyAccount();
		Assert.assertFalse(headerOptions.isLogOutInMyAcDisplayed());
	}

	@Test
	public void verifyLogOutOptionNotVisibleInRightColumnOptionsWithoutLoggedIn() {
		rightColumnOptions = headerOptions.getRightColumnOptions();
		Assert.assertFalse(rightColumnOptions.logoutBtnIsDisplayed());
	}

	@Test
	public void verify() {
		headerOptions.clickOnMyAccount();
		loginPage = headerOptions.clickOnLogin();
		myAccountPage = loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),
				prop.getProperty("validPass"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccount();
		logOutpage = headerOptions.clickOnLogOutOfMyAc();
		Assert.assertEquals(getCurrentPageTitle(driver), "Account Logout");
		navigateBackInBrowser(driver);
		refreshPage(driver);
		Assert.assertEquals(loginPage.getFirstHeading(), "New Customer");
		Assert.assertEquals(loginPage.getSecondHeading(), "Returning Customer");
		myAccountPage = loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),
				prop.getProperty("validPass"));
		Assert.assertTrue(myAccountPage.myAcBreadcrumbIsDisplayed());
		Assert.assertTrue(myAccountPage.editAcInfoLinkIsDisplayed());
	}

	@Test
	public void verifyLogOutPage() {
		headerOptions.clickOnMyAccount();
		loginPage = headerOptions.clickOnLogin();
		myAccountPage = loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),
				prop.getProperty("validPass"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccount();
		logOutpage = headerOptions.clickOnLogOutOfMyAc();
		Assert.assertEquals(getCurrentPageTitle(driver), "Account Logout");
		Assert.assertEquals(getCurrentPageURL(driver), prop.getProperty("logoutPageURL"));
		Assert.assertTrue(logOutpage.logoutPageBreadcrumbIsDisplayed());
		Assert.assertEquals(logOutpage.getLogOutPageHeading(), "Account Logout");
	}

	@Test
	public void verifyUIofLogOutPageLoginOptions() {
		headerOptions.clickOnMyAccount();
		loginPage = headerOptions.clickOnLogin();
		myAccountPage = loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"),
				prop.getProperty("validPass"));
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccount();

		if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ActualUIofLogOutPageOptionfirefoxSS.png");

			Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualUIofLogOutPageOptionfirefoxSS.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLogOutPageOptionfirefoxSS.png"));

		} else if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ActualUIofLogOutPageOptionchromeSS.png");

			Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualUIofLogOutPageOptionchromeSS.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLogOutPageOptionchromeSS.png"));

		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ActualUIofLogOutPageOptionedgeSS.png");

			Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualUIofLogOutPageOptionedgeSS.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLogOutPageOptionedgeSS.png"));

		} else if (browserName.equalsIgnoreCase("safari")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ActualUIofLogOutPageOptionsafariSS.png");

			Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualUIofLogOutPageOptionsafariSS.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLogOutPageOptionsafariSS.png"));

		}

		logOutpage = headerOptions.clickOnLogOutOfMyAc();

		if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ActualUIofLogOutPagefirefoxSS.png");

			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualUIofLogOutPagefirefoxSS.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLogOutPagefirefoxSS.png"));

		} else if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ActualUIofLogOutPagechromeSS.png");

			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualUIofLogOutPagechromeSS.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLogOutPagechromeSS.png"));

		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ActualUIofLogOutPageedgeSS.png");

			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualUIofLogOutPageedgeSS.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLogOutPageedgeSS.png"));

		} else if (browserName.equalsIgnoreCase("safari")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\ExpectedUIofLogOutPagesafariSS.png");

			Assert.assertFalse(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualUIofLogOutPagesafariSS.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedUIofLogOutPagesafariSS.png"));

		}
	}
}
