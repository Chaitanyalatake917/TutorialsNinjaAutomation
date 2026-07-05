package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_021 {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	@Test
	public void verrifyPrivacyPolicyField() {
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		String firstName = "Chaitanya";
		WebElement firstname = driver.findElement(By.xpath("//input[@id='input-firstname']"));
		firstname.sendKeys(firstName);
		String lastName = "Latake";
		WebElement lastname = driver.findElement(By.xpath("//input[@id='input-lastname']"));
		lastname.sendKeys(lastName);
		String email = CommonUtilities.generateDummyMail();
		WebElement eMail = driver.findElement(By.xpath("//input[@id='input-email']"));
		eMail.sendKeys(email);
		String telephone = "7020299142";
		WebElement telePhone = driver.findElement(By.xpath("//input[@id='input-telephone']"));
		telePhone.sendKeys(telephone);
		String password = "123456";
		WebElement Password = driver.findElement(By.xpath("//input[@id='input-password']"));
		Password.sendKeys(password);
		String confirm = "123456";
		WebElement PassConfirm = driver.findElement(By.xpath("//input[@id='input-confirm']"));
		PassConfirm.sendKeys(confirm);
		
		driver.findElement(By.xpath("//input[@value=1 and @name='newsletter']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String expectedWarn="Warning: You must agree to the Privacy Policy!";
		String actualWarning=driver.findElement(By.xpath("//ul[@class='breadcrumb']/following-sibling::div")).getText();

		Assert.assertEquals(actualWarning, expectedWarn);
	}
}
