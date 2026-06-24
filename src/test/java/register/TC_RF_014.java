package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_014 {
	@Test
	public void verifyMandatoryFields() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//a[@class='dropdown-toggle'][@title='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		
		String expectedContent="\"* \"";
		String expectedColor="rgb(255, 0, 0)";
		
		WebElement firstNamelocator=driver.findElement(By.cssSelector("label[for='input-firstname']"));
		String firstNameContent =(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",firstNamelocator);
		Assert.assertEquals(firstNameContent,expectedContent);	
		String firstNameColor=(String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');", firstNamelocator);
		Assert.assertEquals(firstNameColor,expectedColor);
		
		WebElement lastNamelocator=driver.findElement(By.cssSelector("label[for='input-lastname']"));
		String lastNameContent =(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",lastNamelocator);
		Assert.assertEquals(lastNameContent,expectedContent);	
		String lastNameColor=(String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');", lastNamelocator);
		Assert.assertEquals(lastNameColor,expectedColor);
		
		WebElement emailLocator=driver.findElement(By.cssSelector("label[for='input-email']"));
		String emailContent =(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",emailLocator);
		Assert.assertEquals(emailContent,expectedContent);	
		String emailColor=(String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');", emailLocator);
		Assert.assertEquals(emailColor,expectedColor);
		
		WebElement telephoneLocator=driver.findElement(By.cssSelector("label[for='input-telephone']"));
		String telephoneContent =(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",telephoneLocator);
		Assert.assertEquals(telephoneContent,expectedContent);	
		String telephoneColor=(String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');", telephoneLocator);
		Assert.assertEquals(telephoneColor,expectedColor);
		
		WebElement passwordLocator=driver.findElement(By.cssSelector("label[for='input-password']"));
		String passwordContent =(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",passwordLocator);
		Assert.assertEquals(passwordContent,expectedContent);	
		String passwordColor=(String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');", passwordLocator);
		Assert.assertEquals(passwordColor,expectedColor);
		
		WebElement confirmLocator=driver.findElement(By.cssSelector("label[for='input-confirm']"));
		String confirmContent =(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",confirmLocator);
		Assert.assertEquals(confirmContent,expectedContent);	
		String confirmColor=(String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');", confirmLocator);
		Assert.assertEquals(confirmColor,expectedColor);
		
		WebElement privacyPolicyLocator=driver.findElement(By.cssSelector("div[class='pull-right']"));
		String privacyPolicyContent=(String)jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",privacyPolicyLocator);
		Assert.assertEquals(privacyPolicyContent,expectedContent);
		String privacyPolicyColor=(String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');", confirmLocator);
		Assert.assertEquals(privacyPolicyColor,expectedColor);
		
		driver.quit();
	}
}
