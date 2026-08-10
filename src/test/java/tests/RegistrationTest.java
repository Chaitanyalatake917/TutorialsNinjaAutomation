package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pages.HeaderOptions;
import utilities.CommonUtilities;

public class RegistrationTest extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = openBrowserAndAppURL();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccount();
		registrationPage = headerOptions.clickOnRegister();
	}

	@Test
	public void registrationForm() {
		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				CommonUtilities.generateDummyMail(), prop.getProperty("telephone"), prop.getProperty("password"),
				prop.getProperty("confirm"));
		registrationPage.selectPrivacyPolicy();
		accountSuccessPage = registrationPage.clickContinueBtn();

		Assert.assertTrue(accountSuccessPage.isLogoutBtnDisplayed());
		Assert.assertTrue(accountSuccessPage.isSuccessBreadcrumbVisible());

		String properDetails1 = "Your Account Has Been Created!";
		String properDetails2 = "Congratulations! Your new account has been successfully created!";
		String properDetails3 = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String properDetails4 = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String properDetails5 = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";

		String pageContent = accountSuccessPage.getContent();
		Assert.assertTrue(pageContent.contains(properDetails1));
		Assert.assertTrue(pageContent.contains(properDetails2));
		Assert.assertTrue(pageContent.contains(properDetails3));
		Assert.assertTrue(pageContent.contains(properDetails4));
		Assert.assertTrue(pageContent.contains(properDetails5));

		myAccountPage = accountSuccessPage.clickCountinueBtn();

		Assert.assertTrue(myAccountPage.editAcInfoLinkIsDisplayed());
		Assert.assertTrue(myAccountPage.myAcBreadcrumbIsDisplayed());
	}

	@Test
	public void registerWithAllFields() {
		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				CommonUtilities.generateDummyMail(), prop.getProperty("telephone"), prop.getProperty("password"),
				prop.getProperty("confirm"));

		registrationPage.selectYesNewsletterBtn();
		registrationPage.selectPrivacyPolicy();
		accountSuccessPage = registrationPage.clickContinueBtn();

		Assert.assertTrue(accountSuccessPage.isLogoutBtnDisplayed());
		Assert.assertTrue(accountSuccessPage.isSuccessBreadcrumbVisible());

		String properDetails1 = "Your Account Has Been Created!";
		String properDetails2 = "Congratulations! Your new account has been successfully created!";
		String properDetails3 = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String properDetails4 = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String properDetails5 = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";

		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetails1));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetails2));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetails3));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetails4));
		Assert.assertTrue(accountSuccessPage.getContent().contains(properDetails5));

		myAccountPage = accountSuccessPage.clickCountinueBtn();

		Assert.assertTrue(myAccountPage.editAcInfoLinkIsDisplayed());
	}

	@Test
	public void verifyValidationForMandatoryFields() {
		registrationPage.clickContinueBtn();

		String firstnameWarning = "First Name must be between 1 and 32 characters!";
		String lastnameWarning = "Last Name must be between 1 and 32 characters!";
		String emailWarning = "E-Mail Address does not appear to be valid!";
		String telephoneWarning = "Telephone must be between 3 and 32 characters!";
		String passwordWarning = "Password must be between 4 and 20 characters!";
		String privacypolicyWarning = "Warning: You must agree to the Privacy Policy!";

		Assert.assertEquals(registrationPage.firstNameWarning(), firstnameWarning);
		Assert.assertEquals(registrationPage.lastNameWarning(), lastnameWarning);
		Assert.assertEquals(registrationPage.emailWarning(), emailWarning);
		Assert.assertEquals(registrationPage.telePhoneWarning(), telephoneWarning);
		Assert.assertEquals(registrationPage.passwordWarning(), passwordWarning);
		Assert.assertEquals(registrationPage.pageLevelWarning(), privacypolicyWarning);
	}

	@Test
	public void verifyNewsletterIsSelected() {
		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				CommonUtilities.generateDummyMail(), prop.getProperty("telephone"), prop.getProperty("password"),
				prop.getProperty("confirm"));

		registrationPage.selectYesNewsletterBtn();
		registrationPage.selectPrivacyPolicy();
		accountSuccessPage = registrationPage.clickContinueBtn();

		Assert.assertTrue(accountSuccessPage.isLogoutBtnDisplayed());
		myAccountPage = accountSuccessPage.clickCountinueBtn();

		Assert.assertTrue(myAccountPage.subscribeUnsubscribeBtnIsDisplayed());
		subscribeUnsubscribePage = myAccountPage.clickOnSubscribeUnsubscribeBtn();

		Assert.assertTrue(subscribeUnsubscribePage.yesNewsletterBtnIsSelected());
	}

	@Test
	public void verifyNewsletterNotSelected() {
		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				CommonUtilities.generateDummyMail(), prop.getProperty("telephone"), prop.getProperty("password"),
				prop.getProperty("confirm"));

		registrationPage.selectNoNewsletterBtn();
		registrationPage.selectPrivacyPolicy();
		accountSuccessPage = registrationPage.clickContinueBtn();

		Assert.assertTrue(accountSuccessPage.isLogoutBtnDisplayed());
		myAccountPage = accountSuccessPage.clickCountinueBtn();

		Assert.assertTrue(myAccountPage.subscribeUnsubscribeBtnIsDisplayed());
		subscribeUnsubscribePage = myAccountPage.clickOnSubscribeUnsubscribeBtn();

		Assert.assertTrue(subscribeUnsubscribePage.newsletterSubscriptionHeadingIsDisplayed());
		Assert.assertTrue(subscribeUnsubscribePage.noNewsletterBtnIsSelected());
	}

	@Test
	public void verifyRegistrationWays() {

		Assert.assertTrue(registrationPage.registerAccountHeadingIsDisplayed());

		headerOptions = registrationPage.getHeaderOptions();
		headerOptions.clickOnMyAccount();

		loginPage = headerOptions.clickOnLogin();
		registrationPage = loginPage.clickContinueBtn();

		Assert.assertTrue(registrationPage.registerAccountHeadingIsDisplayed());

		headerOptions = registrationPage.getHeaderOptions();
		headerOptions.clickOnMyAccount();
		loginPage = headerOptions.clickOnLogin();

		rightColumnOptions = loginPage.getRightColumnOptions();
		registrationPage = rightColumnOptions.clickOnRegisterBtn();

		Assert.assertTrue(registrationPage.registerAccountHeadingIsDisplayed());
	}

	@Test
	public void passwordConfirmValidation() {
		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				CommonUtilities.generateDummyMail(), prop.getProperty("telephone"), prop.getProperty("correctPassword"),
				prop.getProperty("incorrectPassword"));

		registrationPage.selectPrivacyPolicy();
		registrationPage.clickContinueBtn();

		String warningMsg = "Password confirmation does not match password!";
		Assert.assertEquals(registrationPage.passwordConfirmWarning(), warningMsg);
	}

	@Test
	public void emailIsExistsValidation() {
		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				prop.getProperty("existingMail"), prop.getProperty("telephone"), prop.getProperty("password"),
				prop.getProperty("confirm"));

		registrationPage.selectPrivacyPolicy();
		registrationPage.clickContinueBtn();

		String warningMsg = "Warning: E-Mail Address is already registered!";
		Assert.assertEquals(registrationPage.pageLevelWarning(), warningMsg);
	}

	@Test
	public void verifyInvalidEmailWarning() throws InterruptedException {
		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				prop.getProperty("invalidMail0"), prop.getProperty("telephone"), prop.getProperty("password"),
				prop.getProperty("confirm"));

		registrationPage.selectPrivacyPolicy();
		registrationPage.clickContinueBtn();

		String emailValidation1 = "Please include an '@' in the email address. 'amotoori' is missing an '@'.";
		if (browserName.equalsIgnoreCase("firefox")) {
			emailValidation1 = "Please enter an email address.";
		}
		Assert.assertEquals(registrationPage.emailValidationWarn(), emailValidation1);

		registrationPage.clearEmailField();
		registrationPage.enterEmail(prop.getProperty("invalidMail1"));
		registrationPage.clickContinueBtn();
		String emailValidation2 = "Please enter a part following '@'. 'amotoori@' is incomplete.";
		if (browserName.equalsIgnoreCase("firefox")) {
			emailValidation2 = "Please enter an email address.";
		}

		Assert.assertEquals(registrationPage.emailValidationWarn(), emailValidation2);

		registrationPage.clearEmailField();
		registrationPage.enterEmail(prop.getProperty("invalidMail2"));
		registrationPage.clickContinueBtn();
		String emailValidation3 = "E-Mail Address does not appear to be valid!";

		Assert.assertEquals(registrationPage.emailWarning(), emailValidation3);

		registrationPage.clearEmailField();
		registrationPage.enterEmail(prop.getProperty("invalidMail3"));
		registrationPage.clickContinueBtn();
		String emailValidation4 = "'.' is used at a wrong position in 'gmail.'.";
		if (browserName.equalsIgnoreCase("firefox")) {
			emailValidation4 = "Please enter an email address.";
		}
		Assert.assertEquals(registrationPage.emailValidationWarn(), emailValidation4);
	}

	@Test
	public void verifyInvalidPhoneWarning() {
		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				CommonUtilities.generateDummyMail(), prop.getProperty("invalidTelephone"), prop.getProperty("password"),
				prop.getProperty("confirm"));

		registrationPage.selectNoNewsletterBtn();
		registrationPage.selectPrivacyPolicy();
		accountSuccessPage = registrationPage.clickContinueBtn();

		String expectedWarning = "Invalid mobile no Entered!";

		Assert.assertEquals(registrationPage.telePhoneWarning(), expectedWarning);
		Assert.assertFalse(accountSuccessPage.isSuccessBreadcrumbVisible());
	}

	@Test
	public void verifyRegistrationUsingKeyboard() {
		action = enterMultipleKeysUsingKeyboard(getActions(driver), Keys.TAB, 23);
		action = enterTextUsingActions(action, prop.getProperty("firstname"));
		action = enterMultipleKeysUsingKeyboard(action, Keys.TAB, 1);
		action = enterTextUsingActions(action, prop.getProperty("lastname"));
		action = enterMultipleKeysUsingKeyboard(action, Keys.TAB, 1);
		action = enterTextUsingActions(action, CommonUtilities.generateDummyMail());
		action = enterMultipleKeysUsingKeyboard(action, Keys.TAB, 1);
		action = enterTextUsingActions(action, prop.getProperty("telephone"));
		action = enterMultipleKeysUsingKeyboard(action, Keys.TAB, 1);
		action = enterTextUsingActions(action, prop.getProperty("password"));
		action = enterMultipleKeysUsingKeyboard(action, Keys.TAB, 1);
		action = enterTextUsingActions(action, prop.getProperty("confirm"));
		action = enterMultipleKeysUsingKeyboard(action, Keys.TAB, 1);
		action = enterMultipleKeysUsingKeyboard(action, Keys.ARROW_LEFT, 1);
		action = enterMultipleKeysUsingKeyboard(action, Keys.TAB, 2);
		action = enterMultipleKeysUsingKeyboard(action, Keys.SPACE, 1);
		action = enterMultipleKeysUsingKeyboard(action, Keys.TAB, 1);
		action = enterMultipleKeysUsingKeyboard(action, Keys.ENTER, 1);

		accountSuccessPage = registrationPage.getAccountSuccessPage();
		Assert.assertTrue(accountSuccessPage.isSuccessBreadcrumbVisible());
		rightColumnOptions = accountSuccessPage.getRightColumnOptions();
		Assert.assertTrue(rightColumnOptions.logoutBtnIsDisplayed());
	}

	@Test
	public void verifyPlaceholderValue() {
		Assert.assertEquals(registrationPage.getAttributeOfFirstnameField(), "First Name");
		Assert.assertEquals(registrationPage.getAttributeOfLastnameField(), "Last Name");
		Assert.assertEquals(registrationPage.getAttributeOfEmailField(), "E-Mail");
		Assert.assertEquals(registrationPage.getAttributeOfTelephoneField(), "Telephone");
		Assert.assertEquals(registrationPage.getAttributeOfPasswordField(), "Password");
		Assert.assertEquals(registrationPage.getAttributeOfPasswordConfirmField(), "Password Confirm");
	}

	@Test
	public void verifyMandatoryFields() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		String expectedContent = "\"* \"";
		String expectedColor = "rgb(255, 0, 0)";

		String firstNameContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registrationPage.getlabelOfFirstname());
		Assert.assertEquals(firstNameContent, expectedContent);
		String firstNameColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registrationPage.getlabelOfFirstname());
		Assert.assertEquals(firstNameColor, expectedColor);

		String lastNameContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registrationPage.getlabelOfLastname());
		Assert.assertEquals(lastNameContent, expectedContent);
		String lastNameColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registrationPage.getlabelOfLastname());
		Assert.assertEquals(lastNameColor, expectedColor);

		String emailContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registrationPage.getlabelOfEmail());
		Assert.assertEquals(emailContent, expectedContent);
		String emailColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registrationPage.getlabelOfEmail());
		Assert.assertEquals(emailColor, expectedColor);

		String telephoneContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registrationPage.getlabelOfTelephone());
		Assert.assertEquals(telephoneContent, expectedContent);
		String telephoneColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registrationPage.getlabelOfTelephone());
		Assert.assertEquals(telephoneColor, expectedColor);

		String passwordContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registrationPage.getlabelOfPassword());
		Assert.assertEquals(passwordContent, expectedContent);
		String passwordColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registrationPage.getlabelOfPassword());
		Assert.assertEquals(passwordColor, expectedColor);

		String confirmContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registrationPage.getlabelOfPasswordConfirm());
		Assert.assertEquals(confirmContent, expectedContent);
		String confirmColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registrationPage.getlabelOfPasswordConfirm());
		Assert.assertEquals(confirmColor, expectedColor);

		String privacyPolicyContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registrationPage.getlabelOfPrivacyPolicy());
		Assert.assertEquals(privacyPolicyContent, expectedContent);
		String privacyPolicyColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",
				registrationPage.getlabelOfPrivacyPolicy());
		Assert.assertEquals(privacyPolicyColor, expectedColor);
	}

	@Test
	public void verifyMandatoryFieldsNotNull() {

		registrationPage.fillRegistrationForm("", "", "", "", "", "");

		registrationPage.selectNoNewsletterBtn();
		registrationPage.selectPrivacyPolicy();
		accountSuccessPage = registrationPage.clickContinueBtn();

		String firstNameWarning = "First Name must be between 1 and 32 characters!";
		String lastNameWarning = "Last Name must be between 1 and 32 characters!";
		String emailWarning = "E-Mail Address does not appear to be valid!";
		String telephoneWarning = "Telephone must be between 3 and 32 characters!";

		Assert.assertTrue(registrationPage.firstNameWarning().equals(firstNameWarning));
		Assert.assertTrue(registrationPage.lastNameWarning().equals(lastNameWarning));
		Assert.assertTrue(registrationPage.emailWarning().equals(emailWarning));
		Assert.assertTrue(registrationPage.telePhoneWarning().equals(telephoneWarning));
	}

	@Test(dataProvider = "invalidPasswordProvider", enabled = true)
	public void verifyPasswordValidationsWithInvalidPassword(String password) {
		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				CommonUtilities.generateDummyMail(), prop.getProperty("telephone"), password, password);

		registrationPage.selectNoNewsletterBtn();
		registrationPage.selectPrivacyPolicy();
		accountSuccessPage = registrationPage.clickContinueBtn();

		String expectedWarning = "Entered password does not meet password complexity standards!";

		boolean isValid = password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
		Assert.assertFalse(isValid, "Password meets password complexity standards,hence test passed");
		Assert.assertEquals(registrationPage.passwordWarning(), expectedWarning);

	}

	@DataProvider(name = "invalidPasswordProvider")
	public Object[][] passwordSupplier() {
		Object[][] arr = { { "12345" }, { "abcde" }, { "abcde12345" }, { "ABCDE12345" }, { "ABCDE12345#" },
				{ "ABC$12" } };
		return arr;
	}

	@Test(dataProvider = "validPasswordProvider", enabled = true)
	public void verifyPasswordValidationsWithValidPassword(String password) {
		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				CommonUtilities.generateDummyMail(), prop.getProperty("telephone"), password, password);

		registrationPage.selectNoNewsletterBtn();
		registrationPage.selectPrivacyPolicy();
		accountSuccessPage = registrationPage.clickContinueBtn();

		String expectedWarning = "Entered password does not meet password complexity standards!";

		boolean isValid = password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
		Assert.assertTrue(isValid, "Password doesn't meets password complexity standards,still test passed");
		Assert.assertNotEquals(registrationPage.passwordWarning(), expectedWarning);

	}

	@DataProvider(name = "validPasswordProvider")
	public Object[][] validPasswordSupplier() {
		Object[][] arr = { { "StrongPass123@" }, { "Valid$Password9" }, { "Test@2026" }, { "Secure#Login8" },
				{ "MyPassWord1$" }, { "Abcdef1!" } };
		return arr;
	}

	@Test(dataProvider = "validFirstNameField", enabled = true)
	public void verifyFirstnameFieldWithValidFirstName(String first) {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		Assert.assertEquals(registrationPage.getFirstNameCssValueHeight(), expectedHeight);
		Assert.assertEquals(registrationPage.getFirstNameCssValueWidth(), expectedWidth);

		registrationPage.clearFirstnameField();
		registrationPage.enterFirstName(first);
		registrationPage.clickContinueBtn();
		String expectedWarn = "First Name must be between 1 and 32 characters!";
		boolean isValid = first.matches("^[A-Za-z].{1,32}$");
		// Entered Reg-ex checks whether entered first-name doesn't contains digits &
		// length must be between 1 to 32 chars

		Assert.assertTrue(isValid, "Entered firstname is not following acceptance crieteria");
		Assert.assertNotEquals(registrationPage.firstNameWarning(), expectedWarn);
	}

	@DataProvider(name = "validFirstNameField")
	public Object validFirstNameFieldProvider() {
		return new Object[][] { { "Chaitanya" }, { "c" }, { "chaitanya" }, { "parag" }, { "Omkar" } };
	}

	@Test(dataProvider = "invalidFirstNameField", enabled = true)
	public void verifyFirstnameFieldWithInvalidFirstName(String first) {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		Assert.assertEquals(registrationPage.getFirstNameCssValueHeight(), expectedHeight);
		Assert.assertEquals(registrationPage.getFirstNameCssValueWidth(), expectedWidth);

		registrationPage.clearFirstnameField();
		registrationPage.enterFirstName(first);
		registrationPage.clickContinueBtn();
		String expectedWarn = "First Name must be between 1 and 32 characters!";
		boolean isValid = first.matches("^[A-Za-z]{1,32}$");

		Assert.assertFalse(isValid, "Entered firstname is following acceptance crieteria,hence test fails");
		Assert.assertEquals(registrationPage.firstNameWarning(), expectedWarn);
	}

	@DataProvider(name = "invalidFirstNameField")
	public Object firstNameProvider() {
		return new Object[][] { { "" }, { " " }, { "chaitanya123chaitanya123chaitanya123" }, { "123456" } };
	}

	@Test(dataProvider = "validLastNameField", enabled = true)
	public void verifyLastNameFieldWithValidLastName(String last) {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		Assert.assertEquals(registrationPage.getLastNameCssValueHeight(), expectedHeight);
		Assert.assertEquals(registrationPage.getLastNameCssValueWidth(), expectedWidth);

		registrationPage.clearLastnameField();
		registrationPage.enterLastName(last);
		registrationPage.clickContinueBtn();

		String expectedWarn = "Last Name must be between 1 and 32 characters!";
		boolean isValid = last.matches("^[A-Za-z]{1,32}$");

		Assert.assertTrue(isValid, "Entered lastname is not following acceptance crieteria,hence test fails");
		Assert.assertNotEquals(registrationPage.lastNameWarning(), expectedWarn);
	}

	@DataProvider(name = "validLastNameField")
	public Object validLastNameFieldProvider() {
		return new Object[][] { { "Latake" }, { "l" }, { "patil" }, { "Ingale" } };
	}

	@Test(dataProvider = "invalidLastNameField", enabled = true)
	public void verifyLastNameFieldWithInvalidLastName(String last) {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		Assert.assertEquals(registrationPage.getLastNameCssValueHeight(), expectedHeight);
		Assert.assertEquals(registrationPage.getLastNameCssValueWidth(), expectedWidth);

		registrationPage.clearLastnameField();
		registrationPage.enterLastName(last);
		registrationPage.clickContinueBtn();

		String expectedWarn = "Last Name must be between 1 and 32 characters!";
		boolean isValid = last.matches("^[A-Za-z]{1,32}$");

		Assert.assertFalse(isValid, "Entered lastname is following acceptance crieteria,hence test fails");
		Assert.assertEquals(registrationPage.lastNameWarning(), expectedWarn);
	}

	@DataProvider(name = "invalidLastNameField")
	public Object invalidLastNameProvider() {
		return new Object[][] { { "" }, { " " }, { "latake917latake917latake917latake917" }, { "latake917" },
				{ "78960" } };
	}

	@Test(dataProvider = "validEmailField", enabled = true)
	public void verifyEmailFieldWithValid(String Email) {

		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		Assert.assertEquals(registrationPage.getEmailCssValueHeight(), expectedHeight);
		Assert.assertEquals(registrationPage.getEmailCssValueWidth(), expectedWidth);

		registrationPage.clearEmailField();
		registrationPage.enterEmail(Email);
		registrationPage.clickContinueBtn();

		String expectedWarn = "E-Mail Address does not appear to be valid!";
		boolean isValid = Email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

		Assert.assertTrue(isValid, "Entered Email address is not following acceptance crieteria, hence test failing");
		Assert.assertNotEquals(registrationPage.emailWarning(), expectedWarn);
	}

	@DataProvider(name = "validEmailField")
	public Object validEmailProvider() {
		return new Object[][] { { "john.doe@domain.com" }, { "chaitanyalatake123@gmail.com" },
				{ "user_name@company.org" }, { "employee+tag@workplace.co.in" }, { "student123@university.edu" },
				{ "alpha.beta@research-lab.io" } };
	}

	@Test(dataProvider = "invalidEmailField", enabled = true)
	public void verifyEmailFieldWithInvalid(String Email) {

		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		Assert.assertEquals(registrationPage.getEmailCssValueHeight(), expectedHeight);
		Assert.assertEquals(registrationPage.getEmailCssValueWidth(), expectedWidth);

		registrationPage.clearEmailField();
		registrationPage.enterEmail(Email);
		registrationPage.clickContinueBtn();

//		String expectedWarn = "E-Mail Address does not appear to be valid!";
		boolean isValid = Email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

		Assert.assertFalse(isValid, "Entered Email address is not following acceptance crieteria, hence test failing");
		Assert.assertTrue(registrationPage.emailWarningDisplayed());
//		Assert.assertEquals(registrationPage.emailWarningFirefox(), expectedWarn);
	}

	@DataProvider(name = "invalidEmailField")
	public Object InvalidEmailProvider() {
		return new Object[][] { { "chaitanya.com" }, { "chaitanya@" }, { "@gmail.com" }, { "chaitanya@@gmail.com" },
				{ "chai!tanya@gmail.com" }, { "chaitanya@gmail" } };
	}

	@Test(dataProvider = "invalidTelephoneField", enabled = true)
	public void telephoneValidation(String phone) {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		Assert.assertEquals(registrationPage.getTelephoneCssValueHeight(), expectedHeight);
		Assert.assertEquals(registrationPage.getTelephoneCssValueWidth(), expectedWidth);

		registrationPage.clearTelephoneField();
		registrationPage.enterTelephone(phone);
		registrationPage.clickContinueBtn();
		String expectedWarning = "Telephone must be between 3 and 32 characters!";

		boolean isValid = phone.matches("^[0-9]{3,10}$");

		Assert.assertFalse(isValid, "Telephone must be between 3 and 32 characters!");
		Assert.assertEquals(registrationPage.telePhoneWarning(), expectedWarning);

	}

	@DataProvider(name = "invalidTelephoneField")
	public Object telePhoneProvider() {
		return new Object[][] { { "" }, { " " }, { "abcd1234" }, { "123-456-7890" }, { "+91 9876543210" }, { "7" },
				{ "70" }, { "123456789012345678901234567890123" } };
	}

	@Test(dataProvider = "validTelephoneField", enabled = true)
	public void validTelephoneFieldValidation(String phone) {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		Assert.assertEquals(registrationPage.getTelephoneCssValueHeight(), expectedHeight);
		Assert.assertEquals(registrationPage.getTelephoneCssValueWidth(), expectedWidth);

		registrationPage.clearTelephoneField();
		registrationPage.enterTelephone(phone);
		registrationPage.clickContinueBtn();
		String expectedWarning = "Telephone must be between 3 and 32 characters!";
		boolean isValid = phone.matches("^[0-9]{3,10}$");

		Assert.assertTrue(isValid, "Entered telephone doesn't meet acceptance crieteria");
		Assert.assertNotEquals(registrationPage.telePhoneWarning(), expectedWarning);
	}

	@DataProvider(name = "validTelephoneField")
	public Object validTelephoneFieldProvider() {
		return new Object[][] { { "123" }, { "4567" }, { "9876543210" }, { "2025550173" }, { "777888999" } };
	}

	@Test
	public void validateConfirmAndContinue() {

		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		Assert.assertEquals(registrationPage.getConfirmCssValueHeight(), expectedHeight);
		Assert.assertEquals(registrationPage.getConfirmCssValueWidth(), expectedWidth);

		String expectedButtonTextColor = "rgba(255, 255, 255, 1)";
		if (browserName.equalsIgnoreCase("firefox")) {
			expectedButtonTextColor = "rgb(255, 255, 255)";
		}
		Assert.assertEquals(registrationPage.getContinueCssValueColor(), expectedButtonTextColor);

		String expectedButtonBackgroundColor = "rgba(34, 154, 200, 1)";
		if (browserName.equalsIgnoreCase("firefox")) {
			expectedButtonBackgroundColor = "rgb(34, 154, 200)";
		}
		Assert.assertEquals(registrationPage.getContinueCssValueBackgroundColor(), expectedButtonBackgroundColor);
		Assert.assertEquals(registrationPage.getContinueCssValueFontSize(), "12px");
	}

	@Test
	public void verifyInputTrimming() {
		String expectedEmail = "   " + CommonUtilities.generateDummyMail() + "   ";
		registrationPage.fillRegistrationForm("   " + prop.getProperty("firstname") + "   ",
				"   " + prop.getProperty("lastname") + "   ", expectedEmail,
				"   " + prop.getProperty("telephone") + "   ", "   " + prop.getProperty("password") + "   ",
				"   " + prop.getProperty("confirm") + "   ");

		registrationPage.selectYesNewsletterBtn();
		registrationPage.selectPrivacyPolicy();
		accountSuccessPage = registrationPage.clickContinueBtn();

		myAccountPage = accountSuccessPage.clickCountinueBtn();
		myAccountPage.editAcInfoLinkIsDisplayed();
		myAccountInfoPage = myAccountPage.clickEditAcInfoLink();

		soft = new SoftAssert();

		soft.assertEquals(myAccountInfoPage.getFirstNameValue(), prop.getProperty("firstname"));
		soft.assertEquals(myAccountInfoPage.getLastNameValue(), prop.getProperty("lastname"));
		soft.assertEquals(myAccountInfoPage.getEmailValue(), expectedEmail);
		soft.assertEquals(myAccountInfoPage.getTelephoneValue(), prop.getProperty("telephone"));

		soft.assertAll();
	}

	@Test
	public void verifyCheckBox() {
//		registrationPage.selectPrivacyPolicy();
		Assert.assertFalse(registrationPage.isPrivacyPolicyIsSelected());
	}

	@Test
	public void verifyPrivacyPolicyField() {

		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				CommonUtilities.generateDummyMail(), prop.getProperty("telephone"), prop.getProperty("password"),
				prop.getProperty("confirm"));

		registrationPage.selectYesNewsletterBtn();
		registrationPage.clickContinueBtn();

		Assert.assertEquals(registrationPage.pageLevelWarning(), "Warning: You must agree to the Privacy Policy!");
	}

	@Test
	public void verifyPasswordAndConfirmField() {

		Assert.assertEquals(registrationPage.getTypeAttributeOfPasswordField(), "password");
		Assert.assertEquals(registrationPage.getTypeAttributeOfPasswordConfirmField(), "password");
	}

	@Test
	public void verifyAllSupportedBrowser() {

		registrationPage.fillRegistrationForm(prop.getProperty("firstname"), prop.getProperty("lastname"),
				CommonUtilities.generateDummyMail(), prop.getProperty("telephone"), prop.getProperty("password"),
				prop.getProperty("confirm"));

		registrationPage.selectNoNewsletterBtn();
		registrationPage.selectPrivacyPolicy();
		accountSuccessPage = registrationPage.clickContinueBtn();

		Assert.assertTrue(accountSuccessPage.isLogoutBtnDisplayed());
		registrationPage = accountSuccessPage.getRegistrationPage();
		Assert.assertTrue(accountSuccessPage.isSuccessBreadcrumbVisible());

	}

	@Test
	public void verifyAllLinks() {
		headerOptions = registrationPage.getHeaderOptions();
		contactUsPage = headerOptions.selectContactUs();
		Assert.assertEquals(getCurrentPageTitle(contactUsPage.getDriver()), "Contact Us");
		navigateBackInBrowser(contactUsPage.getDriver());

		headerOptions.clickOnMyAccount();
		registrationPage = headerOptions.clickOnRegister();
		Assert.assertEquals(getCurrentPageTitle(registrationPage.getDriver()), "Register Account");

		headerOptions.clickOnMyAccount();
		loginPage = headerOptions.clickOnLogin();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(contactUsPage.getDriver());

		loginPage = headerOptions.selectWishlist();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(contactUsPage.getDriver());

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
		navigateBackInBrowser(homePage.getDriver());

		registrationPage = headerOptions.selectRegisterBreadcrumb();
		Assert.assertEquals(getCurrentPageTitle(registrationPage.getDriver()), "Register Account");

		rightColumnOptions = headerOptions.getRightColumnOptions();
		loginPage = rightColumnOptions.clickOnLoginBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(homePage.getDriver());

		registrationPage = rightColumnOptions.clickOnRegisterBtn();
		Assert.assertEquals(getCurrentPageTitle(registrationPage.getDriver()), "Register Account");

		forgottenPasswordPage = rightColumnOptions.clickOnForgottenPasswordBtn();
		Assert.assertEquals(getCurrentPageTitle(forgottenPasswordPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgottenPasswordPage.getDriver());

		loginPage = rightColumnOptions.clickOnMyAccountBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnAddressBookBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnWishListBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnOrderHistoryBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnDownloadsBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnRecurringPaymentsBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnRewardPointsBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnReturnsBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnTransactionsBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnNewsletterBtn();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

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
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptions.selectOrderHistory();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptions.selectWishList();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptions.selectNewsletter();
		Assert.assertEquals(getCurrentPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

	}

	@Test
	public void validatePasswordConfirm() {

		registrationPage.enterFirstName(prop.getProperty("firstname"));
		registrationPage.enterLastName(prop.getProperty("lastname"));
		registrationPage.enterEmail(CommonUtilities.generateDummyMail());
		registrationPage.enterTelephone(prop.getProperty("telephone"));
		registrationPage.enterPassword(prop.getProperty("password"));

		registrationPage.selectNoNewsletterBtn();
		registrationPage.selectPrivacyPolicy();
		registrationPage.clickContinueBtn();

		Assert.assertEquals(registrationPage.passwordConfirmWarning(),
				"Password confirmation does not match password!");
	}

	@Test
	public void verifyRegisterPage() {
		Assert.assertEquals(getCurrentPageTitle(driver), "Register Account");
		Assert.assertEquals(getCurrentPageURL(driver), prop.getProperty("registerPageURL"));
		Assert.assertEquals(registrationPage.getCurrentPageHeading(), "Register Account");
		Assert.assertTrue(registrationPage.registerBreadcrumbIsDisaplayed());
	}

	@Test
	public void verifyUIofRegisterAccountPage() {
		if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\ScreenShots\\ActualfirefoxSS.png");

			Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualfirefoxSS.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedfirefoxSS.png"));

		} else if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\ScreenShots\\ActualSSchrome.png");

			Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualSSchrome.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedSSchrome.png"));

		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtilities.takeScreenshot(driver, System.getProperty("user.dir") + "\\Screenshots\\ActualSSedge.png");

			Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualSSedge.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedSSedge.png"));

		} else if (browserName.equalsIgnoreCase("safari")) {
			CommonUtilities.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\ScreenShots\\ExpectedSSsafari.png");

			Assert.assertTrue(CommonUtilities.compareTwoScreenshots(
					System.getProperty("user.dir") + "//ScreenShots//ActualSSsafari.png",
					System.getProperty("user.dir") + "//ScreenShots//ExpectedSSsafari.png"));
		}
	}
}
