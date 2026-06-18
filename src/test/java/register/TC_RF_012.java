package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_012 {

	@Test
	public void verifyRegistrationUsingKeyboard() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		Actions action=new Actions(driver);
		action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB)
		.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER).build().perform();
		
		for(int i=0;i<23;i++) {
			action.sendKeys(Keys.TAB);
		}
		
		action.sendKeys("Chaitanya").sendKeys(Keys.TAB).sendKeys("Latake").sendKeys(Keys.TAB).sendKeys(CommonUtilities.generateDummyMail())
		.sendKeys(Keys.TAB).sendKeys("7020299123").sendKeys(Keys.TAB).sendKeys("12345").sendKeys(Keys.TAB).sendKeys("12345")
		.sendKeys(Keys.TAB).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.SPACE)
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(2)).sendKeys(Keys.ENTER).build().perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Logout']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
		
		driver.quit();
	}
}
