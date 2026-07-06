package register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_RF_023 {
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	@Test
	public void verifyAllLinks() {
		driver.findElement(By.xpath("//ul[@class='list-inline']//a[contains(@href,'contact')]")).click();
		Assert.assertEquals(driver.getTitle(), "Contact Us");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//li[@class='dropdown']/a[@title='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		Assert.assertEquals(driver.getTitle(), "Register Account");
		
		driver.findElement(By.xpath("//li[@class='dropdown']/a[@title='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.id("wishlist-total")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@title='Shopping Cart']")).click();
		Assert.assertEquals(driver.getTitle(), "Shopping Cart");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//span[text()='Shopping Cart']")).click();
		Assert.assertEquals(driver.getTitle(), "Shopping Cart");
		driver.navigate().back();
	
		driver.findElement(By.xpath("//span[text()='Checkout']")).click();
		Assert.assertEquals(driver.getTitle(), "Shopping Cart");
		driver.navigate().back();
	
		driver.findElement(By.linkText("Qafox.com")).click();
		Assert.assertEquals(driver.getTitle(), "Your Store");
		driver.navigate().back();
		
		driver.findElement(By.className("input-group-btn")).click();
		Assert.assertEquals(driver.getTitle(), "Search");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='breadcrumb']/li/a[contains(@href,'home')]")).click();
		Assert.assertEquals(driver.getTitle(), "Your Store");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='breadcrumb']/li/a[contains(@href,'account')]")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='breadcrumb']/li/a[contains(@href,'register')]")).click();
		Assert.assertEquals(driver.getTitle(), "Register Account");
		
		driver.findElement(By.linkText("Login")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.linkText("Register")).click();
		Assert.assertEquals(driver.getTitle(), "Register Account");
		
		driver.findElement(By.linkText("Forgotten Password")).click();
		Assert.assertEquals(driver.getTitle(), "Forgot Your Password?");
		driver.navigate().back();
		
		driver.findElement(By.linkText("My Account")).click();
		Assert.assertEquals(driver.getTitle(), "Register Account");
		
		driver.findElement(By.linkText("Address Book")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.linkText("Wish List")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.linkText("Order History")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.linkText("Downloads")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.linkText("Recurring payments")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.linkText("Reward Points")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.linkText("Returns")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.linkText("Transactions")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.linkText("Newsletter")).click();
		Assert.assertEquals(driver.getTitle(), "Account Login");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='About Us']")).click();
		Assert.assertEquals(driver.getTitle(),"About Us");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Delivery Information']")).click();
		Assert.assertEquals(driver.getTitle(),"Delivery Information");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Privacy Policy']")).click();
		Assert.assertEquals(driver.getTitle(),"Privacy Policy");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Terms & Conditions']")).click();
		Assert.assertEquals(driver.getTitle(),"Terms & Conditions");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Contact Us']")).click();
		Assert.assertEquals(driver.getTitle(),"Contact Us");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Terms & Conditions']")).click();
		Assert.assertEquals(driver.getTitle(),"Terms & Conditions");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Returns']")).click();
		Assert.assertEquals(driver.getTitle(),"Product Returns");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Site Map']")).click();
		Assert.assertEquals(driver.getTitle(),"Site Map");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Brands']")).click();
		Assert.assertEquals(driver.getTitle(),"Find Your Favorite Brand");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Gift Certificates']")).click();
		Assert.assertEquals(driver.getTitle(),"Purchase a Gift Certificate");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Affiliate']")).click();
		Assert.assertEquals(driver.getTitle(),"Affiliate Program");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Specials']")).click();
		Assert.assertEquals(driver.getTitle(),"Special Offers");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='My Account']")).click();
		Assert.assertEquals(driver.getTitle(),"Account Login");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Order History']")).click();
		Assert.assertEquals(driver.getTitle(),"Account Login");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Wish List']")).click();
		Assert.assertEquals(driver.getTitle(),"Account Login");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//footer//a[text()='Newsletter']")).click();
		Assert.assertEquals(driver.getTitle(),"Account Login");
		driver.navigate().back();
		
	}
}
