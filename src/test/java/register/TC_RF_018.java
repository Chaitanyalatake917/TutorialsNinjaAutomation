package register;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utils.CommonUtilities;

public class TC_RF_018 {
	WebDriver driver;
	SoftAssert soft;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "firstNameField", enabled = false)
	public void firstNameValidation(String first) {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		WebElement firstName = driver.findElement(By.id("input-firstname"));
		String actualHeight = firstName.getCssValue("height");
		Assert.assertEquals(actualHeight, expectedHeight);
		String actualWidth = firstName.getCssValue("width");
		Assert.assertEquals(actualWidth, expectedWidth);

		firstName.clear();
		firstName.sendKeys(first);

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		WebElement WarnMsg;
		boolean isVisibleF = false;
		try {
			WarnMsg = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div"));
			isVisibleF = WarnMsg.isDisplayed();
		} catch (Exception e) {
			isVisibleF = false;
		}
		soft = new SoftAssert();
		soft.assertFalse(isVisibleF, "First Name must be between 1 and 32 characters! Your Value: " + first);

		soft.assertAll();
	}

	@Test(dataProvider = "lastNameField", enabled = false)
	public void lastNameValidation(String last) {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		WebElement LastName = driver.findElement(By.id("input-lastname"));
		String actualHeightLastname = LastName.getCssValue("height");
		Assert.assertEquals(actualHeightLastname, expectedHeight);
		String actualWidthLastname = LastName.getCssValue("width");
		Assert.assertEquals(actualWidthLastname, expectedWidth);

		LastName.clear();
		LastName.sendKeys(last);

		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		soft = new SoftAssert();
		WebElement WarnMsg1;
		boolean isVisible = false;
		try {
			WarnMsg1 = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div"));
			isVisible = WarnMsg1.isDisplayed();
		} catch (Exception e) {
			isVisible = false;
		}
		soft.assertFalse(isVisible, "Last Name must be between 1 and 32 characters! Your Value: " + last);
		soft.assertAll();
	}

	@Test(dataProvider = "emailNameField", enabled = false)
	public void emailValidation(String Email) {

		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		WebElement email = driver.findElement(By.id("input-email"));
		String actualHeightemail = email.getCssValue("height");
		Assert.assertEquals(actualHeightemail, expectedHeight);
		String actualWidthemail = email.getCssValue("width");
		Assert.assertEquals(actualWidthemail, expectedWidth);

		email.clear();
		email.sendKeys(Email);
		soft = new SoftAssert();

		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		WebElement WarnMsg2;
		boolean isVisibleE = false;
		try {
			if (!email.getAttribute("validationMessage").isEmpty()) {
				isVisibleE = true;
			} else {
				WarnMsg2 = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div"));
				isVisibleE = WarnMsg2.isDisplayed();
			}
		} catch (Exception e) {
			isVisibleE = false;
		}
		soft.assertFalse(isVisibleE, "E-Mail Address does not appear to be valid! Your Value: " + Email);
		soft.assertAll();
	}

	@Test(dataProvider = "telephoneField",enabled=false)
	public void telephoneValidation(String phone) {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		WebElement telePhone = driver.findElement(By.xpath("//input[@id='input-telephone']"));
		String actualHeight = telePhone.getCssValue("height");
		Assert.assertEquals(actualHeight, expectedHeight);
		String actualWidth = telePhone.getCssValue("width");
		Assert.assertEquals(actualWidth, expectedWidth);

		telePhone.clear();
		telePhone.sendKeys(phone);
		soft = new SoftAssert();
		String expectedWarning = "Telephone must be between 3 and 32 characters!";
		String displayedWarning = "";

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		try {
			displayedWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div"))
					.getText();
		} catch (NoSuchElementException e) {
			displayedWarning = "";
		}
		soft.assertNotEquals(displayedWarning, expectedWarning, "Value must be between 3 and 32 characters!");
		soft.assertAll();
	}

	@Test(dataProvider = "passwordField", enabled = true)
	public void passwordValidation(String pass) {

		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		WebElement password = driver.findElement(By.id("input-password"));
		String actualHeightpassword = password.getCssValue("height");
		Assert.assertEquals(actualHeightpassword, expectedHeight);
		String actualWidthpassword = password.getCssValue("width");
		Assert.assertEquals(actualWidthpassword, expectedWidth);

		password.clear();
		password.sendKeys(pass);
		soft = new SoftAssert();

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		boolean isVisibleE = false;
		try {
			isVisibleE = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div"))
					.isDisplayed();
		} catch (Exception e) {
			isVisibleE = false;
		}
		soft.assertFalse(isVisibleE, "Password must be between 4 and 20 characters! Your Value: " + pass);
		soft.assertAll();
	}
	
	@Test
	public void validateConfirmAndContinue() {

		String expectedHeight = "34px";
		String expectedWidth = "701.25px";
		
		WebElement confirm = driver.findElement(By.id("input-confirm"));
		String actualHeightConfirm = confirm.getCssValue("height");
		Assert.assertEquals(actualHeightConfirm, expectedHeight);
		String actualWidthConfirm = confirm.getCssValue("width");
		Assert.assertEquals(actualWidthConfirm, expectedWidth);
		
		WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		String actualButtonTextColor = continueButton.getCssValue("color");
		Assert.assertEquals(actualButtonTextColor,"rgba(255, 255, 255, 1)");
		String actualButtonBackgroundColor = continueButton.getCssValue("background-color");
		Assert.assertEquals(actualButtonBackgroundColor,"rgba(34, 154, 200, 1)");
		String actualButtonFontSize = continueButton.getCssValue("font-size");
		Assert.assertEquals(actualButtonFontSize,"12px");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(srcScreenshot,new File("C:\\Users\\chait\\eclipse-workspace\\TutorialsNinjaAutomationProject\\Screenshots\\AcutalRAPageAligment.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Assert.assertFalse(CommonUtilities.compareTwoScreenshots("C:\\Users\\chait\\eclipse-workspace\\TutorialsNinjaAutomationProject\\Screenshots\\AcutalRAPageAligment.png", "C:\\Users\\chait\\eclipse-workspace\\TutorialsNinjaAutomationProject\\Screenshots\\ExpectedRAPageAligment.png"));
		
	}

	@DataProvider(name = "firstNameField")
	public Object firstNameProvider() {
		return new Object[][] { { "Chaitanya" }, { "" }, { " " }, { "chaitanya123" }, { "c" },
				{ "chaitanya123chaitanya123chaitanya123" }, { "123456" } };
	}

	@DataProvider(name = "lastNameField")
	public Object lastNameProvider() {
		return new Object[][] { { "Latake" }, { "" }, { " " }, { "l" }, { "latake917latake917latake917latake917" },
				{ "latake917" }, { "78960" } };
	}

	@DataProvider(name = "emailNameField")
	public Object emailNameProvider() {
		return new Object[][] { { "chaitanyalatake123@gmail.com" }, { "chaitanya.com" }, { "chaitanya@" },
				{ "@gmail.com" }, { "chaitanya@@gmail.com" }, { "chai!tanya@gmail.com" }, { "chaitanya@gmail" } };
	}

	@DataProvider(name = "telephoneField")
	public Object telePhoneProvider() {
		return new Object[][] { { "" }, { " " }, { "abcd1234" }, { "123-456-7890" }, { "+91 9876543210" }, { "7" },
				{ "70" }, { "123456789012345678901234567890123" } };
	}
	
	@DataProvider(name="passwordField")
	public Object passwordProvider() {
		return new Object[][] {{""},{"12345678901234567890"},{"1"},{"12"},{"123"},{"1234"}};
	}
}
