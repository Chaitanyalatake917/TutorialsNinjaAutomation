package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_010 {

	@Test
	public void verifyInvalidEmailWarning() throws InterruptedException{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Chaitanya");
		driver.findElement(By.id("input-lastname")).sendKeys("Latake");
		driver.findElement(By.id("input-email")).sendKeys("amotoori");
		driver.findElement(By.id("input-telephone")).sendKeys("7020299142");
		driver.findElement(By.id("input-password")).sendKeys("Abc@134");
		driver.findElement(By.id("input-confirm")).sendKeys("Abc@134");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String emailValidation1="Please include an '@' in the email address. 'amotoori' is missing an '@'.";
		String actualValidation1=driver.findElement(By.id("input-email")).getAttribute("validationMessage");
		Assert.assertEquals(emailValidation1, actualValidation1);	
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String emailValidation2="Please enter a part following '@'. 'amotoori@' is incomplete.";
		String actualValidation2=driver.findElement(By.id("input-email")).getAttribute("validationMessage");
		Assert.assertEquals(emailValidation2, actualValidation2);	

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String emailValidation3="E-Mail Address does not appear to be valid!";
		Thread.sleep(300);
		String actualValidation3=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();;
		Assert.assertEquals(emailValidation3, actualValidation3);	
		System.out.println(actualValidation3);
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail.");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String emailValidation4="'.' is used at a wrong position in 'gmail.'.";
		String actualValidation4=driver.findElement(By.id("input-email")).getAttribute("validationMessage");
		Assert.assertEquals(emailValidation4, actualValidation4);	
		
		driver.quit();	
	}
}
