package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_011 {

	@Test
	public void verifyInvalidPhoneWarning() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Chaitanya");
		driver.findElement(By.id("input-lastname")).sendKeys("Latake");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateDummyMail());
		driver.findElement(By.id("input-telephone")).sendKeys("abcd");
		driver.findElement(By.id("input-password")).sendKeys("Abc@134");
		driver.findElement(By.id("input-confirm")).sendKeys("Abc@134");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String expectedWarning="Invalid mobile no Entered!";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), expectedWarning);	
		
		Assert.assertFalse(driver.findElement(By.linkText("Success")).isDisplayed());
	}
}
