package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utils.CommonUtilities;

public class TC_RF_019 {
	WebDriver driver;
	SoftAssert soft;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void verifyInputTrimming() {
		
		String firstName = "   Chaitanya   ";
		WebElement firstname = driver.findElement(By.xpath("//input[@id='input-firstname']"));
		firstname.sendKeys(firstName);
		String lastName = "   Latake   ";
		WebElement lastname = driver.findElement(By.xpath("//input[@id='input-lastname']"));
		lastname.sendKeys(lastName);
		String email = "   " + CommonUtilities.generateDummyMail() + "   ";
		WebElement eMail = driver.findElement(By.xpath("//input[@id='input-email']"));
		eMail.sendKeys(email);
		String telephone = "   7020299142   ";
		WebElement telePhone = driver.findElement(By.xpath("//input[@id='input-telephone']"));
		telePhone.sendKeys(telephone);
		String password = "   123456   ";
		WebElement Password = driver.findElement(By.xpath("//input[@id='input-password']"));
		Password.sendKeys(password);
		String confirm = "   123456   ";
		WebElement PassConfirm = driver.findElement(By.xpath("//input[@id='input-confirm']"));
		PassConfirm.sendKeys(confirm);
		
		driver.findElement(By.xpath("//input[@value=1 and @name='newsletter']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		driver.findElement(By.xpath("//div[@class='pull-right']/a[text()='Continue']")).click();
		driver.findElement(By.xpath("//a[text()='Edit your account information']")).click();
		
		soft=new SoftAssert();
		
		String actualFirstname=driver.findElement(By.name("firstname")).getDomAttribute("value");
		soft.assertEquals(actualFirstname,firstName.trim());

		String actualLastname=driver.findElement(By.name("lastname")).getDomAttribute("value");
		soft.assertEquals(actualLastname,lastName.trim());
		
		String actualEmail=driver.findElement(By.name("email")).getDomAttribute("value");
		soft.assertEquals(actualEmail,email.trim());
		
		String actualTelephone=driver.findElement(By.name("telephone")).getDomAttribute("value");
		soft.assertEquals(actualTelephone,telephone.trim());
		
		soft.assertAll();
	}
}
