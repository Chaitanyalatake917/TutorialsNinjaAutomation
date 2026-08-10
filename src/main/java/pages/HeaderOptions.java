package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class HeaderOptions extends RootPage {
	
	public HeaderOptions(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@title='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Register")
	private WebElement register;

	@FindBy(linkText = "Login")
	private WebElement login;

	@FindBy(xpath = "//ul[@class='list-inline']//a[contains(@href,'contact')]")
	private WebElement callSymbol;

	@FindBy(id = "wishlist-total")
	private WebElement wishList;

	@FindBy(xpath = "//a[@title='Shopping Cart']")
	private WebElement shoppingCart1;

	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement selectShoppingCart2;

	@FindBy(xpath = "//span[text()='Checkout']")
	private WebElement checkout;

	@FindBy(linkText = "Qafox.com")
	private WebElement qafoxLink;

	@FindBy(className = "input-group-btn")
	private WebElement searchBtn;

	@FindBy(linkText = "Logout")
	private WebElement logOutBtn;
	
	@FindBy(xpath="//a[@class='dropdown-toggle']/following-sibling::ul//*[text()='Logout']")
	private WebElement logOutMyAc;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchInputPlaceholder;
	
	public void enterValueInSearchPlaceholder(String value) {
		elementUtilities.enterTextIntoElement(searchInputPlaceholder, value);
	}
	
	public String getPlaceholderTextOfSearchBar() {
		return elementUtilities.getDomAttributeOfElement(searchInputPlaceholder, "placeholder");
	}
	
	public boolean isLogOutInMyAcDisplayed() {
		return elementUtilities.isElementIsDisplayed(logOutMyAc);
	}

	public LogOutPage clickOnLogOutBtn() {
		elementUtilities.clickOnElement(logOutBtn);
		return new LogOutPage(driver);
	}

	public void clickOnMyAccount() {
		elementUtilities.clickOnElement(myAccountDropMenu);
	}

	public LoginPage clickOnLogin() {
		elementUtilities.clickOnElement(login);
		return new LoginPage(driver);
	}
	
	public LogOutPage clickOnLogOutOfMyAc() {
		elementUtilities.clickOnElement(logOutMyAc);
		return new LogOutPage(driver);
	}

	public RegistrationPage clickOnRegister() {
		elementUtilities.clickOnElement(register);
		return new RegistrationPage(driver);
	}

	public ContactUsPage selectContactUs() {
		elementUtilities.clickOnElement(callSymbol);
		return new ContactUsPage(driver);
	}

	public LoginPage selectWishlist() {
		elementUtilities.clickOnElement(wishList);
		return new LoginPage(driver);
	}

	public ShoppingCartPage selectShoppingCart1() {
		elementUtilities.clickOnElement(shoppingCart1);
		return new ShoppingCartPage(driver);
	}

	public ShoppingCartPage selectShoppingCart2() {
		elementUtilities.clickOnElement(selectShoppingCart2);
		return new ShoppingCartPage(driver);
	}

	public ShoppingCartPage selectCheckOut() {
		elementUtilities.clickOnElement(checkout);
		return new ShoppingCartPage(driver);
	}

	public HomePage selectLogo() {
		elementUtilities.clickOnElement(qafoxLink);
		return new HomePage(driver);
	}

	public SearchPage selectSearchBtn() {
		elementUtilities.clickOnElement(searchBtn);
		return new SearchPage(driver);
	}

}
