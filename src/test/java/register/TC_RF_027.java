package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_027 {

	@Test(dataProvider = "browserProvider")
	public void verifyAllSupportedBrowser(String browser) {
		WebDriver driver = null;

		if (browser == "chrome") {
			driver = new ChromeDriver();
		} else if (browser == "edge") {
			driver = new EdgeDriver();
		} else if (browser == "firefox") {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtilities.generateDummyMail());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[contains(text(),'Success')]")).isDisplayed());

		driver.quit();
	}

	@DataProvider(name = "browserProvider")
	public Object[][] browserProvider() {
		return new Object[][] { { "chrome" }, { "edge" }, { "firefox" } };
	}

}
