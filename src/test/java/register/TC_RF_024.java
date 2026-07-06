package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_024 {
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://tutorialsninja.com/demo/");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	@Test
	public void validatePasswordConfirm() {
		driver.findElement(By.xpath("//li[@class='dropdown']/a[@title='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		Assert.assertEquals(driver.getTitle(), "Register Account");
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Chaitanya");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Latake");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(CommonUtilities.generateDummyMail());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9975196273");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("9975196273");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String expectedWarn="Password confirmation does not match password!";
		String actualWarn=driver.findElement(By.xpath("//input[@id='input-confirm']/following-sibling::div")).getText();
		
		Assert.assertEquals(actualWarn, expectedWarn);
	}
}
