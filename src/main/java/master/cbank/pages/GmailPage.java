package master.cbank.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import master.cbank.lib.PageUtils;
import master.cbank.utils.ReadInputFiles;
import master.cbank.utils.ReadProperties;

public class GmailPage {
	
	public GmailPage(WebDriver driver) throws IOException {
		this.driver = driver;
		page = new PageUtils();
		readfiles = new ReadInputFiles();
		properties = ReadProperties.readProperties();
		objectrepo = ReadProperties.readObjectRepo();
		jsonObject = readfiles.readJsonFile();
		// TODO Auto-generated constructor stub
	}

	WebDriver driver;
	PageUtils page;
	ReadInputFiles readfiles;
	HashMap<String, String> properties = new HashMap<String, String>();
	HashMap<String, String> objectrepo = new HashMap<String, String>();
	JSONObject jsonObject;
	
/**
 * This method is to verify the email 
 *  
 */
	
  public void verifyEmail() {
	  
	  try {
		  this.driver.get("https://gmail.com");
		  Thread.sleep(4000);
		  this.driver.findElement(By.xpath(this.objectrepo.get("gmail.login.user"))).sendKeys(properties.get("gUserName"));
		  Thread.sleep(4000);
		  this.driver.findElement(By.xpath(this.objectrepo.get("gmail.login.Next"))).click();
		  Thread.sleep(4000);
    	  this.driver.findElement(By.xpath(this.objectrepo.get("gmail.login.pass"))).sendKeys(properties.get("gPassword"));
    	  Thread.sleep(4000);
    	  this.driver.findElement(By.xpath(this.objectrepo.get("gmail.login.Next"))).click();
    	  Thread.sleep(4000);
    	  this.driver.findElement(By.xpath(this.objectrepo.get("gmail.login.email"))).click();
    	  Thread.sleep(4000);
    	  this.driver.findElement(By.xpath(this.objectrepo.get("gmail.login.email2"))).click();
    	  Thread.sleep(4000);
    	  String oldTab = this.driver.getWindowHandle();
    	  System.out.println(oldTab);
    	  this.driver.findElement(By.xpath(this.objectrepo.get("gmail.login.activate"))).click();
    	  Thread.sleep(12000);

    	  ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    	  System.out.println(tabs.size());
    	  System.out.println(tabs.get(0));
    	  String oldTab1 = this.driver.getWindowHandle();
    	  System.out.println(oldTab1);
    	  driver.switchTo().window(tabs.get(1));
    	  System.out.println(tabs.get(1));
    	  String oldTab2 = this.driver.getWindowHandle();
    	  System.out.println(oldTab2);
    
    	  //tabs.remove(oldTab);
    	 // this.driver.switchTo().window(tabs.get(1));
    	  this.driver.findElement(By.id(this.objectrepo.get("cbank.master.users.password"))).sendKeys("ABcd12$#");
    	  Thread.sleep(4000);
    	  this.driver.findElement(By.id(this.objectrepo.get("cbank.master.users.cnfpassword"))).sendKeys("ABcd12$#");
    	  Thread.sleep(4000);
    	  this.driver.findElement(By.id(this.objectrepo.get("cbank.master.users.submit"))).click();
    	
    	  
    	  
		  
	  }catch(Exception ex) {
		  
	  }
	  
  }
	
	
}
