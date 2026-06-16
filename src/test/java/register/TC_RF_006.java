package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class TC_RF_006 {

	public void verifyNewsletterIsNotSelected() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://tutorialsninja.com/demo)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Chaitanya");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Latake");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(utils.CommonUtilities.generateDummyMail());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@name='newsletter' and @value=0]")).click();
		driver.findElement(By.xpath("//input[@name='agree' and @value=1]")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		driver.findElement(By.linkText("Continue")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).isDisplayed());
		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value=0 and @name='newsletter']")).isSelected());
		
		driver.quit();
	}
}
