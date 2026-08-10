package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.root.RootPage;

public class SearchPage extends RootPage {

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//li/a[text()='Search']")
	private WebElement searchBreadcrumb;

	@FindBy(xpath = "//h4/a[contains(text(),'HP')]")
	private WebElement resultanantHPProductHeading;

	@FindBy(xpath = "//h4/a[contains(text(),'iMac')]")
	private WebElement resultantImacProductHeading;

	@FindBy(linkText = "iMac")
	private WebElement resultantMacProduct;

	@FindBy(xpath = "//div[@class='product-thumb']")
	private List<WebElement> pl;

	@FindBy(id = "input-search")
	private WebElement searchCriteriaPlaceholder;

	@FindBy(id = "button-search")
	private WebElement searchBtn;

	@FindBy(id = "description")
	private WebElement searchInProductDescriptionCheckbox;

	@FindBy(name = "category_id")
	private WebElement categoryDropdown;
	
	@FindBy(xpath="//input[@name='sub_category']/..")
	private WebElement searchInSubCategoriesCheckBox;
	
	private By resultPara=By.xpath("//input[@id='button-search']/following-sibling::p");

	private By searchResultMsg = By.xpath("//div[@id='content']/h2/following-sibling::p");
	
	public void selectSearchInSubCategoriesCheckBox() {
		elementUtilities.clickOnElement(searchInSubCategoriesCheckBox);
	}

	public void selectProductCategory(String option) {
		elementUtilities.selectOptionByvalue(categoryDropdown, option);
	}

	public void selectSearchInProductDescriptionCheckbox() {
		elementUtilities.clickOnElement(searchInProductDescriptionCheckbox);
	}

	public void clickOnSelectBtn() {
		elementUtilities.clickOnElement(searchBtn);
	}

	public String getSearchCriteriaPlaceholderValue() {
		return elementUtilities.getDomAttributeOfElement(searchCriteriaPlaceholder, "placeholder");
	}

	public void enterIntoSearchCriteria(String value) {
		elementUtilities.enterTextIntoElement(searchCriteriaPlaceholder, value);
	}

	public int countOfProductResult() {
		return pl.size();
	}

	public String getSearchResultMsg() {
		return elementUtilities.getTextOfElement(searchResultMsg);
	}

	public boolean isSearchBreadcrumbIsDisplayed() {
		return elementUtilities.isElementIsDisplayed(searchBreadcrumb);
	}

	public boolean isHPProductisDisplayedinResult() {
		return elementUtilities.isElementIsDisplayed(resultanantHPProductHeading);
	}

	public boolean isImacProductIsDisplayed() {
		return elementUtilities.isElementIsDisplayed(resultantImacProductHeading);
	}

	public boolean isProductIsDisplayedUsingDescription() {
		return elementUtilities.isElementIsDisplayed(resultantMacProduct);
	}
	
	public String getResultParagraph() {
		return elementUtilities.getTextOfElement(resultPara);
	}
}
