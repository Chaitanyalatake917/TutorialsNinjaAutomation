package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class RegistrationPage extends RootPage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='content']/h1[text()='Register Account']")
	private WebElement registerAccountHeading;

	@FindBy(id = "input-firstname")
	private WebElement firstName;

	@FindBy(id = "input-lastname")
	private WebElement lastName;

	@FindBy(id = "input-email")
	private WebElement eMail;

	@FindBy(id = "input-telephone")
	private WebElement telePhone;

	@FindBy(id = "input-password")
	private WebElement passWord;

	@FindBy(id = "input-confirm")
	private WebElement passConfirm;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement privacyPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueBtn;

	@FindBy(xpath = "//input[@name='newsletter' and @value=1]")
	private WebElement yesNewsletterBtn;

	@FindBy(xpath = "//input[@name='newsletter' and @value=0]")
	private WebElement noNewsletterBtn;

	private By firstNameWarn = By.xpath("//input[@id='input-firstname']/following-sibling::div");

	private By lastNameWarn = By.xpath("//input[@id='input-lastname']/following-sibling::div");

	private By emailWarn = By.xpath("//input[@id='input-email']/following-sibling::div");

	private By telephoneWarn = By.xpath("//input[@id='input-telephone']/following-sibling::div");

	private By passwordWarn = By.xpath("//input[@id='input-password']/following-sibling::div");

	private By passwordConfirmWarn = By.xpath("//input[@id='input-confirm']/following-sibling::div");

	private By emailAlreadyExistsWarning = By
			.xpath("//ul[@class='breadcrumb']/following-sibling::div[contains(text(),'Warning')]");

	@FindBy(xpath = "//ul[@class='breadcrumb']/li/a[text()='Register']")
	private WebElement registerBreadcrumb;

	@FindBy(css = "label[for='input-firstname']")
	private WebElement labelOfFirstname;

	@FindBy(css = "label[for='input-lastname']")
	private WebElement labelOfLastname;

	@FindBy(css = "label[for='input-email']")
	private WebElement labelOfEmail;

	@FindBy(css = "label[for='input-telephone']")
	private WebElement labelOfTelephone;

	@FindBy(css = "label[for='input-password']")
	private WebElement labelOfPassword;

	@FindBy(css = "label[for='input-confirm']")
	private WebElement labelOfPasswordConfirm;

	@FindBy(css = "div[class='pull-right']")
	private WebElement labelOfPrivacyPolicy;

	@FindBy(linkText = "login page")
	private WebElement loginPageLink;
	
	public void fillRegistrationForm(String firstname, String lastname, String mail, String telephone, String password,String confirm) {
		enterFirstName(firstname);
		enterLastName(lastname);
		enterEmail(mail);
		enterTelephone(telephone);
		enterPassword(password);
		enterPasswordConfirm(confirm);
	}

	public LoginPage clickOnLoginPageLink() {
		elementUtilities.clickOnElement(loginPageLink);
		return new LoginPage(driver);
	}

	public WebElement getlabelOfFirstname() {
		return labelOfFirstname;
	}

	public boolean registerBreadcrumbIsDisaplayed() {
		return elementUtilities.isElementIsDisplayed(registerBreadcrumb);
	}

	public WebElement getlabelOfLastname() {
		return labelOfLastname;
	}

	public WebElement getlabelOfEmail() {
		return labelOfEmail;
	}

	public WebElement getlabelOfTelephone() {
		return labelOfTelephone;
	}

	public WebElement getlabelOfPassword() {
		return labelOfPassword;
	}

	public WebElement getlabelOfPasswordConfirm() {
		return labelOfPasswordConfirm;
	}

	public WebElement getlabelOfPrivacyPolicy() {
		return labelOfPrivacyPolicy;
	}

	public String getAttributeOfFirstnameField() {
		return elementUtilities.getDomAttributeOfElement(firstName, "placeholder");
	}

	public String getAttributeOfLastnameField() {
		return elementUtilities.getDomAttributeOfElement(lastName, "placeholder");
	}

	public String getAttributeOfTelephoneField() {
		return elementUtilities.getDomAttributeOfElement(telePhone, "placeholder");
	}

	public String getAttributeOfEmailField() {
		return elementUtilities.getDomAttributeOfElement(eMail, "placeholder");
	}

	public String getAttributeOfPasswordField() {
		return elementUtilities.getDomAttributeOfElement(passWord, "placeholder");
	}

	public String getAttributeOfPasswordConfirmField() {
		return elementUtilities.getDomAttributeOfElement(passConfirm, "placeholder");
	}

	public boolean registerAccountHeadingIsDisplayed() {
		return elementUtilities.isElementIsDisplayed(registerAccountHeading);
	}

	public void enterFirstName(String firstname) {
		elementUtilities.enterTextIntoElement(firstName, firstname);
	}

	public void enterLastName(String lastname) {
		elementUtilities.enterTextIntoElement(lastName, lastname);
	}

	public void enterEmail(String email) {
		elementUtilities.enterTextIntoElement(eMail, email);
	}

	public void enterTelephone(String telephone) {
		elementUtilities.enterTextIntoElement(telePhone, telephone);
	}

	public void enterPassword(String password) {
		elementUtilities.enterTextIntoElement(passWord, password);
	}

	public void enterPasswordConfirm(String confirm) {
		elementUtilities.enterTextIntoElement(passConfirm, confirm);
	}

	public void selectPrivacyPolicy() {
		elementUtilities.clickOnElement(privacyPolicy);
	}

	public void selectYesNewsletterBtn() {
		elementUtilities.clickOnElement(yesNewsletterBtn);
	}

	public void selectNoNewsletterBtn() {
		elementUtilities.clickOnElement(noNewsletterBtn);
	}

	public String firstNameWarning() {
		return elementUtilities.getTextOfElement(firstNameWarn);
	}

	public String lastNameWarning() {
		return elementUtilities.getTextOfElement(lastNameWarn);
	}

	public String emailWarning() {
		return elementUtilities.getTextOfElement(emailWarn);
	}
	
	public boolean emailWarningDisplayed() {
		if(elementUtilities.isElementIsDisplayed(emailWarn)) {
			return true;
		}else if(emailWarningFirefox()!=null && !emailWarningFirefox().isEmpty()){
			return true;
		}
		return false;
	}

	public String emailWarningFirefox() {
		return elementUtilities.getDomPropertyOfElement(eMail, "validationMessage");
	}

	public String emailAlreadyExistsWarning() {
		return elementUtilities.getTextOfElement(emailAlreadyExistsWarning);
	}

	public String telePhoneWarning() {
		return elementUtilities.getTextOfElement(telephoneWarn);
	}

	public String passwordWarning() {
		return elementUtilities.getTextOfElement(passwordWarn);
	}

	public String passwordConfirmWarning() {
		return elementUtilities.getTextOfElement(passwordConfirmWarn);
	}

	public AccountSuccessPage clickContinueBtn() {
		elementUtilities.clickOnElement(continueBtn);
		return new AccountSuccessPage(driver);
	}

	public String emailValidationWarn() {
		return elementUtilities.getDomPropertyOfElement(eMail, "validationMessage");
	}

	public void clearEmailField() {
		elementUtilities.clearTextFromElement(eMail);
	}

	public void clearFirstnameField() {
		elementUtilities.clearTextFromElement(firstName);
	}

	public String getFirstNameCssValueHeight() {
		return elementUtilities.getCssPropertyOfElement(firstName, "height");
	}

	public String getFirstNameCssValueWidth() {
		return elementUtilities.getCssPropertyOfElement(firstName, "width");
	}

	public void clearLastnameField() {
		elementUtilities.clearTextFromElement(lastName);
	}

	public void clearTelephoneField() {
		elementUtilities.clearTextFromElement(telePhone);
	}

	public String getLastNameCssValueWidth() {
		return elementUtilities.getCssPropertyOfElement(lastName, "width");
	}

	public String getLastNameCssValueHeight() {
		return elementUtilities.getCssPropertyOfElement(lastName, "height");
	}

	public String getEmailCssValueWidth() {
		return elementUtilities.getCssPropertyOfElement(eMail, "width");
	}

	public String getEmailCssValueHeight() {
		return elementUtilities.getCssPropertyOfElement(eMail, "height");
	}

	public String getValidationMessageOfEmailField() {
		return elementUtilities.getDomPropertyOfElement(eMail, "validationMessage");
	}

	public String getTelephoneCssValueWidth() {
		return elementUtilities.getCssPropertyOfElement(telePhone, "width");
	}

	public String getTelephoneCssValueHeight() {
		return elementUtilities.getCssPropertyOfElement(telePhone, "height");
	}

	public String getConfirmCssValueHeight() {
		return elementUtilities.getCssPropertyOfElement(passConfirm, "height");
	}

	public String getContinueCssValueColor() {
		return elementUtilities.getCssPropertyOfElement(continueBtn, "color");
	}

	public String getConfirmCssValueWidth() {
		return elementUtilities.getCssPropertyOfElement(passConfirm, "width");
	}

	public String getContinueCssValueBackgroundColor() {
		return elementUtilities.getCssPropertyOfElement(continueBtn, "background-color");
	}

	public String getContinueCssValueFontSize() {
		return elementUtilities.getCssPropertyOfElement(continueBtn, "font-size");
	}

	public boolean isPrivacyPolicyIsSelected() {
		return elementUtilities.isElementIsSelected(privacyPolicy);
	}

	public String getTypeAttributeOfPasswordField() {
		return elementUtilities.getDomAttributeOfElement(passWord, "type");
	}

	public String getTypeAttributeOfPasswordConfirmField() {
		return elementUtilities.getDomAttributeOfElement(passConfirm, "type");
	}

}
