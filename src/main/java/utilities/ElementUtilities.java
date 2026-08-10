package utilities;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.By;
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
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	}

	public String getTextOfElement(By locator) {
	    try {
	        WebElement element = driver.findElement(locator);

	        if (element.isDisplayed()) {
	            return element.getText();
	        }
	    } catch (NoSuchElementException | StaleElementReferenceException e) {
	        return "";
	    }
	    return "";
	}

	public void clickOnElement(WebElement element) {
		if (isElementIsDisplayed(element) && element.isEnabled()) {
			element.click();
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
			 	WebElement element1=wait.until(ExpectedConditions.presenceOfElementLocated(element));
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
		if(isElementIsDisplayed(element) && element.isEnabled() && (Objects.equals(getDomAttributeOfElement(element,"readonly"),null)||!(getDomAttributeOfElement(element,"readonly").equals("true")))) {
			element.clear();
		}
	}
	
	public void enterTextIntoElement(WebElement element,String text) {
		if(isElementIsDisplayed(element) && element.isEnabled()) {
			clearTextFromElement(element);
			element.sendKeys(text);
		}
	}
	public String getCssPropertyOfElement(WebElement element,String property) {
		String value="";
		value=element.getCssValue(property);
		return value;
	}

	public String getDomPropertyOfElement(WebElement element, String attribute) {
		return element.getDomProperty(attribute);
	}

	public String getDomAttributeOfElement(WebElement element, String attribute) {
		return element.getDomAttribute(attribute);
	}
	
	public void copyTextOfFieldUsingKeyboard(WebElement element,WebDriver driver) {
		Actions action = new Actions(driver);
		action.click(element).keyDown(Keys.CONTROL).sendKeys("a").sendKeys("c").keyUp(Keys.CONTROL).build().perform();
	}
	
	public void pasteTextIntoFieldUsingKeyboard(WebElement element,WebDriver driver) {
		Actions action = new Actions(driver);
		action.click(element).keyDown(Keys.CONTROL).sendKeys("a").sendKeys(Keys.BACK_SPACE).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
	}
	
	public void selectOptionByvalue(WebElement element,String value) {
		try {
			Select select=new Select(element);
			select.selectByValue(value);
		}catch(NoSuchElementException e) {
			System.out.println("Option not found: "+e);
		}catch(Exception e) {
			System.out.println("Unexpected Error Occurred "+e);
		}
	}
}
