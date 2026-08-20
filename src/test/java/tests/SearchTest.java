package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.HeaderOptions;

public class SearchTest extends Base{
	
	@BeforeMethod
	public void setup() {
		driver=openBrowserAndAppURL();
		headerOptions=new HeaderOptions(driver);
	}
	
	@Test
	public void searchWithExistingProduct() {
		headerOptions.enterValueInSearchPlaceholder(prop.getProperty("existingproduct"));
		searchPage=headerOptions.selectSearchBtn();
		Assert.assertTrue(searchPage.isSearchBreadcrumbIsDisplayed());
		Assert.assertTrue(searchPage.isHPProductisDisplayedinResult());
	}
	
	@Test
	public void searchWithNonExistingProduct() {
		headerOptions.enterValueInSearchPlaceholder(prop.getProperty("nonexistingproduct"));
		searchPage=headerOptions.selectSearchBtn();
		Assert.assertTrue(searchPage.isSearchBreadcrumbIsDisplayed());
		Assert.assertEquals(searchPage.getSearchResultMsg(),"There is no product that matches the search criteria.");
	}
	
	@Test
	public void searchWithoutAnyProduct() {
		searchPage=headerOptions.selectSearchBtn();
		Assert.assertTrue(searchPage.isSearchBreadcrumbIsDisplayed());
		Assert.assertEquals(searchPage.getSearchResultMsg(),"There is no product that matches the search criteria.");
	}
	
	@Test
	public void searchWithExistingProductAfterLoggedIn() {
		headerOptions.clickOnMyAccount();
		loginPage=headerOptions.clickOnLogin();
		myAccountPage=loginPage.enterEmailAndPasswordAndClickContinue(prop.getProperty("validMail"), prop.getProperty("validPass"));
		headerOptions=myAccountPage.getHeaderOptions();
		headerOptions.enterValueInSearchPlaceholder(prop.getProperty("existingproduct"));
		searchPage=headerOptions.selectSearchBtn();
		Assert.assertTrue(searchPage.isSearchBreadcrumbIsDisplayed());
		Assert.assertTrue(searchPage.isHPProductisDisplayedinResult());
	}
	
	@Test
	public void searchWithExistingMultipleProducts() {
		headerOptions.enterValueInSearchPlaceholder(prop.getProperty("existingmultipleproduct"));
		searchPage=headerOptions.selectSearchBtn();
		Assert.assertTrue(searchPage.countOfProductResult()>0);
	}
	
	@Test
	public void verifyPlaceholderOfSearchBar() {
		Assert.assertEquals(headerOptions.getPlaceholderTextOfSearchBar(), "Search");
		searchPage=headerOptions.selectSearchBtn();
		Assert.assertEquals(searchPage.getSearchCriteriaPlaceholderValue(),"Keywords");
	}
	
	@Test
	public void verifySearchingUsingSearchCriteria() {
		searchPage=headerOptions.selectSearchBtn();
		searchPage.enterIntoSearchCriteria(prop.getProperty("existingproduct"));
		searchPage.clickOnSelectBtn();
		Assert.assertTrue(searchPage.isHPProductisDisplayedinResult());
	}
	
	@Test
	public void verifySearchUsingTextFromProductDescription() {
		searchPage=headerOptions.selectSearchBtn();
		searchPage.enterIntoSearchCriteria(prop.getProperty("existingproductdescription"));
		searchPage.selectSearchInProductDescriptionCheckbox();
		searchPage.clickOnSelectBtn();
		Assert.assertTrue(searchPage.isProductIsDisplayedUsingDescription());
	}
	
	@Test
	public void verifySearchBySelectingCategoryOfProduct() {
		searchPage=headerOptions.selectSearchBtn();
		searchPage.enterIntoSearchCriteria(prop.getProperty("existingproductname"));
		searchPage.selectProductCategory(prop.getProperty("correctcategoryvalue"));
		searchPage.clickOnSelectBtn();
		Assert.assertTrue(searchPage.isImacProductIsDisplayed());
		searchPage.selectProductCategory(prop.getProperty("wrongcategoryvalue"));
		searchPage.clickOnSelectBtn();
		Assert.assertEquals(searchPage.getResultParagraph(),"There is no product that matches the search criteria.");
	}
	
	@Test
	public void verifySearchBySelectingSearchInSubCategories() {
		searchPage=headerOptions.selectSearchBtn();
		searchPage.enterIntoSearchCriteria(prop.getProperty("existingproductname"));
		searchPage.selectProductCategory(prop.getProperty("parentcategorydesktopvalue"));
		searchPage.clickOnSelectBtn();
		Assert.assertEquals(searchPage.getResultParagraph(),"There is no product that matches the search criteria.");
		searchPage.selectSearchInSubCategoriesCheckBox();
		searchPage.clickOnSelectBtn();
		Assert.assertTrue(searchPage.isImacProductIsDisplayed());
	}
}
