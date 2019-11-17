package master.cbank.pages;

import java.io.IOException;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import master.cbank.lib.PageUtils;
import master.cbank.utils.ReadInputFiles;
import master.cbank.utils.ReadProperties;

public class AccountsPage {
	WebDriver driver;
	PageUtils page;
	ReadInputFiles readfiles;
	HashMap<String, String> properties = new HashMap<String, String>();
	HashMap<String, String> objectrepo = new HashMap<String, String>();
	JSONObject jsonObject;
	
	/***************************************************************
	 * This is constructor of page .
	 * @param driver
	 * @throws IOException
	 ***************************************************************/

	public AccountsPage(WebDriver driver) throws IOException {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		page = new PageUtils();
		readfiles = new ReadInputFiles();
		properties = ReadProperties.readProperties();
		objectrepo = ReadProperties.readObjectRepo();
		jsonObject = readfiles.readJsonFile();
	}

	/*******************************************************
	 * This method intend is to navigate to user page
	 * 
	 * @author bhanu
	 * @return 
	 * 
	 ******************************************************/
	public String NavigateToAccounts() {
		String sMessage= null;
		try {
			Thread.sleep(4000);
			//clicking on the icon
			System.out.println(this.objectrepo.get("cbank.master.global.icon"));
			WebElement e = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.global.icon")));
			e.click();

			Thread.sleep(4000);
			//clicking on account  icon
			System.out.println(this.objectrepo.get("cbank.master.account.icon"));
			WebElement ex = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.account.icon")));
			ex.click();
			
			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.accounts.headermsg"));
			WebElement eb1 = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.accounts.headermsg")));
			if (eb1.isDisplayed() == true) {
				sMessage = eb1.getText();
				System.out.println(sMessage);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("Unable to Navigate to Users.");
		}return sMessage;
	}

	/******************************************************************
	 * @@author bhanu
	 * @param none
	 * @return 
	 * @return none
	 * 
	 *         This method is to create an users
	 * 
	 ******************************************************************/
	public String createAccount() {
		  String sMessage = null;
		try {
			Thread.sleep(4000);
			//clicking on creating account
			System.out.println(this.objectrepo.get("cbank.master.account.createaccount"));
			WebElement ex = this.driver
					.findElement(By.xpath(this.objectrepo.get("cbank.master.account.createaccount")));
			ex.click();

			// selecting the account type.
			Select sec = new Select(
					this.driver.findElement(By.id(this.objectrepo.get("cbank.master.account.accountype"))));
			sec.selectByValue("test");
			
			//giving  the nick name
			WebElement nick = this.driver
					.findElement(By.id(this.objectrepo.get("cbank.master.account.accounnickname")));
			nick.sendKeys(this.properties.get("cAccount"));
			
			String sAccountStatus= this.verifyAccount();
			if(sAccountStatus.equalsIgnoreCase("Congratulations, the Nickname is available!")) {
				//clicking on the creating the account.
				WebElement acc = this.driver
						.findElement(By.xpath(this.objectrepo.get("cbank.master.account.createAccount1")));
				acc.click();
				String str = this.properties.get("cAccount");
				
				if(this.driver.getPageSource().contains(str)) {
					sMessage = "The account is created.";
				}else {
					sMessage="The account is faile to crated";
				}	
				
			}else {
				
				sMessage="The account is faile to crate";
				
			}
			
			

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("Unable to cre.ate an account");
		}return sMessage;
	}
	
	/********************************************************************
	 * This method intent to is verify the account is already exisited.
	 * 
	 * 
	 * 
	 ***************************************************************/
	
	public String verifyAccount() {
		 String sMessage =null;
		try {
			//Verifying element is alrady there!!
		
			
			Thread.sleep(4000);
			WebElement nick = this.driver
					.findElement(By.id(this.objectrepo.get("cbank.master.account.crtmsg")));
			if (nick.isDisplayed() == true) {
				
				System.out.println(nick.getText());
				sMessage = nick.getText();
			}else {
				sMessage = "account already existed.";
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("account is alredy existed.");
			
		}return sMessage;
		
		
		
	}

}
