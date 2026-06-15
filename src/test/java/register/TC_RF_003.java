package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_003 {
	@Test
	public static void registerWithAllFields() {
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
		driver.findElement(By.id("input-confirm")).sendKeys("Abc@134");
		driver.findElement(By.xpath("//input[@name='newsletter' and @value=1]")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
		
		String properDetails1="Your Account Has Been Created!";
		String properDetails2="Congratulations! Your new account has been successfully created!";
		String properDetails3="You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String properDetails4="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String properDetails5="A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";
		
		Assert.assertTrue(driver.findElement(By.id("content")).getText().contains(properDetails1));
		Assert.assertTrue(driver.findElement(By.id("content")).getText().contains(properDetails2));
		Assert.assertTrue(driver.findElement(By.id("content")).getText().contains(properDetails3));
		Assert.assertTrue(driver.findElement(By.id("content")).getText().contains(properDetails4));
		Assert.assertTrue(driver.findElement(By.id("content")).getText().contains(properDetails5));
		
		driver.findElement(By.linkText("Continue")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
		driver.quit();
	}
}
