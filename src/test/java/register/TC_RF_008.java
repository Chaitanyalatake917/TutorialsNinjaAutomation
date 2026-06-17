package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_008 {

	@Test
	public void passwordConfirmValidation() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Chaitanya");
		driver.findElement(By.id("input-lastname")).sendKeys("Latake");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateDummyMail());
		driver.findElement(By.id("input-telephone")).sendKeys("7020299142");
		driver.findElement(By.id("input-password")).sendKeys("Abc@134");
		driver.findElement(By.id("input-confirm")).sendKeys("Abcd@134");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String warningMsg="Password confirmation does not match password!";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-confirm']/following-sibling::div")).getText(), warningMsg);
		
		driver.quit();
	}
}
