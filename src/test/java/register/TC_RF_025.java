package register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_025 {
	
	@Test
	public void verifyRegisterPage() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Register Account");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=account/register");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#content>h1")).getText(), "Register Account");
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']/li/a[text()='Register']")).isDisplayed());
		
		driver.quit();
	}
}
