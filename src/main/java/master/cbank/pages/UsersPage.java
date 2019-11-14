package master.cbank.pages;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import master.cbank.lib.PageUtils;
import master.cbank.utils.ReadInputFiles;
import master.cbank.utils.ReadProperties;

public class UsersPage {
	WebDriver driver;
	PageUtils page;
	ReadInputFiles readfiles;
	HashMap<String, String> properties = new HashMap<String, String>();
	HashMap<String, String> objectrepo = new HashMap<String, String>();
	JSONObject jsonObject;
	public UsersPage(WebDriver driver) throws IOException {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		page = new PageUtils();
		readfiles = new ReadInputFiles();
		properties = ReadProperties.readProperties();
		objectrepo = ReadProperties.readObjectRepo();
		jsonObject = readfiles.readJsonFile();
		//System.out.println(objectrepo);

	}
/**
 * This method intend is to navigate to user page
 * 
 * 
 */
	public void NavigateToUsers() {
		try {
			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.global.icon"));
			WebElement e = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.global.icon")));
			e.click();
			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.users.icon"));
			WebElement ex = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.users.icon")));
			ex.click();
			
		}catch(Exception ex) {
			System.out.println("Unable to Navigate to Users.");
		}
	}
/***
 * Creating users
 * 
 * 
 * 
 */
		public void createUsers() {
			try {
				Thread.sleep(4000);
				System.out.println(this.objectrepo.get("cbank.master.users.newuser"));
				WebElement e = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.users.newuser")));
				e.click();
				

				
				Thread.sleep(4000);
				System.out.println(this.objectrepo.get("cbank.master.users.email"));
				WebElement ex = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.users.email")));
				
				String name = (String) this.jsonObject.get("Username");
				System.out.println(name);
				ex.sendKeys(name);
				
				//selecting the language.
				Select sec = new Select(this.driver.findElement(By.id(this.objectrepo.get("cbank.master.users.lang"))));
				String lang = (String) this.jsonObject.get("language");
				System.out.println(lang);
				sec.selectByVisibleText(lang);
				
				Thread.sleep(4000);
				System.out.println(this.objectrepo.get("cbank.master.users.desc"));
				WebElement eDesc = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.users.desc")));			
				String Description = (String) this.jsonObject.get("Description");
				System.out.println(Description);
				eDesc.sendKeys(Description);
				
				Thread.sleep(4000);
				System.out.println(this.objectrepo.get("cbank.master.users.fname"));
				WebElement eFname = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.users.fname")));			
				String sFName = (String) this.jsonObject.get("FirstName");
				System.out.println(sFName);
				eFname.sendKeys(sFName);
				
				Thread.sleep(4000);
				System.out.println(this.objectrepo.get("cbank.master.users.lname"));
				WebElement eLname = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.users.lname")));			
				String sLname = (String) this.jsonObject.get("LastName");
				System.out.println(sLname);
				eLname.sendKeys(sLname);
				
				Thread.sleep(4000);
				System.out.println(this.objectrepo.get("cbank.master.users.save"));
				WebElement eSave = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.users.save")));			
				eSave.click();
				
			}catch(Exception ex) {
				System.out.println("Unable to complete to Users.");
			}
		
	}
	
	
	
	
}
