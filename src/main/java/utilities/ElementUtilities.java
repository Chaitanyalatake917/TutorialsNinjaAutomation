package utilities;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtilities {
	protected WebDriver driver;
	protected WebDriverWait wait;

	public ElementUtilities(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public WebElement getElement(By ele) {
		return driver.findElement(ele);
	}

	public String getTextOfElement(By locator) {

		if (isElementIsDisplayed(locator)) {
			WebElement element = driver.findElement(locator);
			return element.getText();
		} else {
			return "";
		}
	}
	
	public void clickOnElement(By element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element))).click();

		} catch (TimeoutException | ElementClickInterceptedException e) {
			throw new RuntimeException("Unable to click element: " + element, e);
		}
	}

	public void clickOnElement(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();

		} catch (TimeoutException | ElementClickInterceptedException | StaleElementReferenceException e) {
			throw new RuntimeException("Unable to click element: " + element, e);
		}
	}

	public void clickOnElementUsingJS(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			
		} catch (TimeoutException | StaleElementReferenceException | JavascriptException e) {
			throw new RuntimeException("Unable to click element using JavaScript: " + element, e);
		}
	}

	public boolean isElementIsDisplayed(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public boolean isElementIsDisplayed(By element) {
		try {
			WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			return element1.isDisplayed();
		} catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public boolean isElementIsSelected(WebElement element) {
		boolean b = false;
		if (isElementIsDisplayed(element)) {
			b = element.isSelected();
		}
		return b;
	}

	public void clearTextFromElement(WebElement element) {
		if (isElementIsDisplayed(element) && element.isEnabled()
				&& (Objects.equals(getDomAttributeOfElement(element, "readonly"), null)
						|| !(getDomAttributeOfElement(element, "readonly").equals("true")))) {
			element.clear();
		}
	}

	public void enterTextIntoElement(WebElement element, String text) {
		if (isElementIsDisplayed(element) && element.isEnabled()) {
			clearTextFromElement(element);
			element.sendKeys(text);
		}
	}
	
	public void enterTextIntoElement(By element, String text) {
		if (isElementIsDisplayed(element) && getElement(element).isEnabled()) {
			clearTextFromElement(getElement(element));
			getElement(element).sendKeys(text);
		}
	}

	public String getCssPropertyOfElement(WebElement element, String property) {
		String value = "";
		value = element.getCssValue(property);
		return value;
	}

	public String getDomPropertyOfElement(WebElement element, String attribute) {
		return element.getDomProperty(attribute);
	}
	
	public String getDomPropertyOfElement(By element, String attribute) {
		return getElement(element).getDomProperty(attribute);
	}

	public String getDomAttributeOfElement(WebElement element, String attribute) {
		return element.getDomAttribute(attribute);
	}
	
	public String getDomAttributeOfElement(By element, String attribute) {
		return getElement(element).getDomAttribute(attribute);
	}

	public void copyTextOfFieldUsingKeyboard(WebElement element, WebDriver driver) {
		Actions action = new Actions(driver);
		action.click(element).keyDown(Keys.CONTROL).sendKeys("a").sendKeys("c").keyUp(Keys.CONTROL).build().perform();
	}

	public void pasteTextIntoFieldUsingKeyboard(WebElement element, WebDriver driver) {
		Actions action = new Actions(driver);
		action.click(element).keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.BACK_SPACE).sendKeys("v")
				.keyUp(Keys.CONTROL).build().perform();
	}
	
	public void pasteTextIntoFieldUsingKeyboard(By element, WebDriver driver) {
		Actions action = new Actions(driver);
		action.click(getElement(element)).keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.BACK_SPACE).sendKeys("v")
				.keyUp(Keys.CONTROL).build().perform();
	}

	public void selectOptionByvalue(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.selectByValue(value);
		} catch (NoSuchElementException e) {
			System.out.println("Option not found: " + e);
		} catch (Exception e) {
			System.out.println("Unexpected Error Occurred " + e);
		}
	}
}
