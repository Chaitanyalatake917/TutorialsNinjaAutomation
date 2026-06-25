package register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_017 {
	 WebDriver driver;
	 
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(dataProvider="passwordProvider")
	public void verifyPasswordValidations(String password) {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Chaitanya");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Latake");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(CommonUtilities.generateDummyMail());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9876543210");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String expectedWarning="Entered password does not meet password complexity standards!";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), expectedWarning);
	}
	
	@DataProvider(name="passwordProvider")
	public Object[][] passwordSupplier(){
		Object[][] arr= {
				{"12345"},
				{"abcde"},
				{"abcde12345"},
				{"ABCDE12345"},
				{"ABCDE12345#"},
				{"ABC$12"}
		};
		return arr;
	}
}
