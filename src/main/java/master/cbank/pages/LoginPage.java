package master.cbank.pages;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import master.cbank.utils.ReadProperties;

public class LoginPage {	

	WebDriver driver;
	HashMap<String, String> properties = new HashMap<String, String>();
    public LoginPage(WebDriver driver) throws IOException {
    	
    	this.driver = driver;
    	properties = ReadProperties.readProperties();

   	}
	/**
	 * This method will do login to user page 
	 * @throws Exception 
	 * 
	 * 
	 */
    
    public WebDriver Login() throws Exception {
    	try {
    		
    	  this.driver.findElement(By.id("username")).sendKeys(properties.get("sUserName")); 	  
    	  this.driver.findElement(By.id("password")).sendKeys(properties.get("sPasswd"));
       	  this.driver.findElement(By.xpath("//button[@type='submit']")).click();
       	  
   
    }catch(Exception ex) {
       System.out.println("Unable to login" + ex.getMessage());
    	
    }
    	return this.driver;
    }
    
}
    
