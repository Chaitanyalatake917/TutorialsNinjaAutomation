package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class LoginPage extends RootPage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='well']/a[text()='Continue']")
	private WebElement continueBtn;

	@FindBy(id = "input-email")
	private WebElement eMail;

	@FindBy(id = "input-password")
	private WebElement passWord;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginBtn;

	@FindBy(linkText = "Forgotten Password")
	private WebElement forgotPassword;
	
	private By firstHeading=By.xpath("(//div[@id='content']//h2)[1]");
	
	private By secondHeading=By.xpath("(//div[@id='content']//h2)[2]");

	public MyAccountPage enterEmailAndPasswordAndClickContinue(String email, String password) {
		elementUtilities.enterTextIntoElement(eMail, email);
		elementUtilities.enterTextIntoElement(passWord, password);
		clickLoginBtn();
		return new MyAccountPage(driver);
	}

	public boolean isForgottenPasswordLinkDisplayed() {
		return elementUtilities.isElementIsDisplayed(forgotPassword);
	}

	public ForgottenPasswordPage clickForgottenPassword() {
		elementUtilities.clickOnElement(forgotPassword);
		return new ForgottenPasswordPage(driver);
	}

	public MyAccountPage clickLoginBtn() {
		elementUtilities.clickOnElement(loginBtn);
		return new MyAccountPage(driver);
	}

	public void enterPasswordField(String pass) {
		elementUtilities.enterTextIntoElement(passWord, pass);
	}

	public void enterEmailField(String email) {
		elementUtilities.enterTextIntoElement(eMail, email);
	}

	public RegistrationPage clickContinueBtn() {
		elementUtilities.clickOnElement(continueBtn);
		return new RegistrationPage(driver);
	}

	public String getEmailIdPlaceholderValue() {
		return eMail.getDomAttribute("placeholder");
	}

	public String getPasswordPlaceholderValue() {
		return passWord.getDomAttribute("placeholder");
	}

	public String getDomPropertyOfEmail() {
		return elementUtilities.getDomPropertyOfElement(eMail, "value");
	}

	public String getDomAttributeOfPassword() {
		return elementUtilities.getDomAttributeOfElement(passWord, "type");
	}

	public void copyTextOfpasswordField() {
		elementUtilities.copyTextOfFieldUsingKeyboard(passWord, driver);
	}

	public void pasteTextIntoEmailField() {
		elementUtilities.pasteTextIntoFieldUsingKeyboard(eMail, driver);
	}

	public String getPageSrcCodeOfLoginPage(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public String getFirstHeading() {
		return elementUtilities.getTextOfElement(firstHeading);
	}
	
	public String getSecondHeading() {
		return elementUtilities.getTextOfElement(secondHeading);
	}
}
