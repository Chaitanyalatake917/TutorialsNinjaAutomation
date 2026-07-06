package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_026 {
	WebDriver driver = new ChromeDriver();

	@Test
	public void verifyUIofRegisterAccountPage() {
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.xpath("//li[@class='dropdown']/a[contains(@href,'account')]")).click();
		driver.findElement(By.linkText("Register")).click();

		CommonUtilities.takeScreenshot(driver, System.getProperty("user.dir") + "\\Screenshots\\ExpectedSS.png");
		try {
			Assert.assertFalse(
					CommonUtilities.compareTwoScreenshots(System.getProperty("user.dir") + "//ScreenShots//ActualSS.png",
							System.getProperty("user.dir") + "//ScreenShots//ExpectedSS.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
