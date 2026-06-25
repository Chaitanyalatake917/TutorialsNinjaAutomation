package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtilities;

public class TC_RF_015 {
	@Test
	void verifyEnteredUserDetails() {
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("http://localhost/opencart/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//span[@class and text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		String firstname="Chaitanya";
		String lastname="Latake";
		String email=CommonUtilities.generateDummyMail().toLowerCase();
		String password="123456";
		
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		
		driver.findElement(By.id("input-firstname")).sendKeys(firstname);
		driver.findElement(By.id("input-lastname")).sendKeys(lastname);
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		
		WebElement agree=driver.findElement(By.xpath("//input[@class='form-check-input' and @name='agree']"));
		jse.executeScript("arguments[0].click();", agree);
		
		WebElement continueBtn=driver.findElement(By.xpath("//button[text()='Continue']"));
		jse.executeScript("arguments[0].click();", continueBtn);
		
		String dbUrl="jdbc:mysql://127.0.0.1:3306/opencart";
        String user="root";
        String pass="";

        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;
        
        String firstNameDB=null;
        String lastNameDB=null;
        String emailDB=null;
        
        try{
        	// Step 1: Establish the connection
            conn= DriverManager.getConnection(dbUrl,user,pass);
            
         // Step 2: Create a statement
            statement= conn.createStatement();
            
         // Step 3: Execute a query
            String query="SELECT * FROM `oc_customer`;";
            result= statement.executeQuery(query);

         // Step 4: Process the result set
            while (result.next()){
                 firstNameDB=result.getString("firstname");
                 lastNameDB=result.getString("lastname");
                 emailDB=result.getString("email");  
            }
        }catch(Exception e){
        		e.printStackTrace();
        }finally {
            // Step 5: Clean up the resources
            try {
                if (result!= null) result.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        
        Assert.assertEquals(firstNameDB,firstname);
        Assert.assertEquals(lastNameDB, lastname);
        Assert.assertEquals(emailDB, email);
        
		driver.quit();
	}
}
