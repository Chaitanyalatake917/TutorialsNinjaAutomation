package register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_RF_16 {

	WebDriver driver;
	SoftAssert soft;
	
	@AfterMethod
	public void tearDown() {
		try {
			soft.assertAll();
		}finally {
			driver.quit();
		}
	}
	
	@Test
	public void verifyMandatoryFieldsNotNull(){
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		soft=new SoftAssert();
		soft.assertEquals(driver.getTitle(),"Your Store One");
		
		String firstName="     ";
		String lastName="     ";
		String email="     ";
		String telephone="     ";
		String password="     ";
		String confirm="     ";
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(telephone);
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(confirm);
		
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String firstNameWarning="First Name must be between 1 and 32 characters!";
		String lastNameWarning="Last Name must be between 1 and 32 characters!";
		String emailWarning="E-Mail Address does not appear to be valid!";
		String telephoneWarning="Telephone must be between 3 and 32 characters!";
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(), firstNameWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(), lastNameWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), emailWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), telephoneWarning);
	}
}
