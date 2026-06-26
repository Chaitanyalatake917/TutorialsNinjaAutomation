package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_RF_018 {
	WebDriver driver;
	SoftAssert soft;

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "firstNameSupplier")
	public void verifyHeightWidthAcceptanceValue(String firstname, String lastname) {
		driver = new ChromeDriver();
		soft = new SoftAssert();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();

		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		String actualHeight = driver.findElement(By.id("input-firstname")).getCssValue("height");
		Assert.assertEquals(actualHeight, expectedHeight);
		String actualWidth = driver.findElement(By.id("input-firstname")).getCssValue("width");
		Assert.assertEquals(actualWidth, expectedWidth);

		WebElement firstName = driver.findElement(By.id("input-firstname"));
		firstName.clear();
		firstName.sendKeys(firstname);
		
		String actualHeightLastname = driver.findElement(By.id("input-lastname")).getCssValue("height");
		Assert.assertEquals(actualHeightLastname, expectedHeight);
		String actualWidthLastname = driver.findElement(By.id("input-lastname")).getCssValue("width");
		Assert.assertEquals(actualWidthLastname, expectedWidth);

		WebElement LastName = driver.findElement(By.id("input-lastname"));
		LastName.clear();
		LastName.sendKeys(lastname);

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		boolean isVisible = false;
		try {
			isVisible = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div"))
					.isDisplayed();
		} catch (Exception e) {
			isVisible = false;
		}
		soft.assertFalse(isVisible, "First Name must be between 1 and 32 characters! Your Value: " + firstname);
		
		boolean isVisibleL = false;
		try {
			isVisibleL = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div"))
					.isDisplayed();
		} catch (Exception e) {
			isVisibleL = false;
		}
		soft.assertFalse(isVisibleL, "Last Name must be between 1 and 32 characters! Your Value: " + lastname);
		soft.assertAll();
	}

	@DataProvider(name = "firstNameSupplier")
	public Object[][] firstNameProvider() {
		Object[][] firstname = { { "Chaitanya","Latake" }, { "","" }, { " "," " }, { "chaitanya123","latake917" }, { "c","l" },
				{ "chaitanya123chaitanya123chaitanya123","latake917latake917latake917latake917" }, { "123456","78960" } };
		return firstname;
	}
}
