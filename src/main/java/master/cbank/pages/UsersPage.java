package master.cbank.pages;

import java.io.IOException;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

	/*******************************************************************
	 * contrusctor of the page
	 * 
	 * 
	 * @param driver
	 * @throws IOException
	 *******************************************************************/

	public UsersPage(WebDriver driver) throws IOException {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		page = new PageUtils();
		readfiles = new ReadInputFiles();
		properties = ReadProperties.readProperties();
		objectrepo = ReadProperties.readObjectRepo();
		jsonObject = readfiles.readJsonFile();
		// System.out.println(objectrepo);

	}

	/*****************************************************************
	 * This method intend is to navigate to user page
	 * 
	 * @return String
	 * 
	 * 
	 ****************************************************************/
	public String NavigateToUsers() {
		String sMessage = null;
		try {

			// clicking on global icon
			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.global.icon"));
			WebElement e = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.global.icon")));
			e.click();

			// clicking on the users icon
			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.users.icon"));
			WebElement ex = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.users.icon")));
			ex.click();
			// getting the message after clicking the users page.

			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.users.headermsg"));
			WebElement ex1 = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.users.headermsg")));
			if (ex1.isDisplayed() == true) {
				sMessage = ex1.getText();
				System.out.println(sMessage);
			}

		} catch (Exception ex) {
			Assert.fail("Unable to Navigate to Users.");
			ex.printStackTrace();
			System.out.println("Unable to Navigate to Users.");

		}
		return sMessage;
	}

	/********************************************************************
	 * this method intent to create users .
	 * 
	 * @return
	 * 
	 * 
	 * 
	 ********************************************************************/
	public String createUser() {
		String sMessage = null;
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

			// selecting the language.
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

			Thread.sleep(4000);

			System.out.println(this.objectrepo.get("cbank.master.users.duplicate"));
			WebElement eDuplicate = this.driver
					.findElement(By.xpath(this.objectrepo.get("cbank.master.users.duplicate")));
			if (eDuplicate.isDisplayed() == true) {
				sMessage = eDuplicate.getText();
				System.out.println(sMessage);
			} else {
				WebElement ePermissions = this.driver
						.findElement(By.xpath(this.objectrepo.get("cbank.master.users.dupmsg")));
				sMessage = ePermissions.getText();
				System.out.println(sMessage);
			}

		} catch (Exception ex) {
			Assert.fail("Unable to create to Users.");
			ex.printStackTrace();

		}
		return sMessage;

	}

	/*****************************************************************
	 * @method assignPermissions this method intenth to give permissions to the
	 *         users.
	 * 
	 * 
	 ****************************************************************/
	public void assignPermissions() {
		try {
			System.out.println(this.objectrepo.get("cbank.master.global.button"));
			WebElement e = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.global.button")));
			e.click();

			WebElement we = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.users.manageuser")));
			JavascriptExecutor js = (JavascriptExecutor) this.driver;
			js.executeScript("arguments[0].scrollIntoView();", we);

			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.users.manageuser"));
			WebElement ex = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.users.manageuser")));
			ex.click();

			this.enablePermisssions();

		} catch (Exception ex) {
			Assert.fail("Unable to assign to Users.");
			ex.printStackTrace();

		}

	}

	/**************************************************************
	 * this method intent is enable permission in the user level.
	 * 
	 * 
	 * 
	 **************************************************************/
	public void enablePermisssions() {
		try {
			System.out.println(this.objectrepo.get("cbank.master.userper.manticket"));
			WebElement e = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.userper.manticket")));
			e.click();

			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.userper.orderticket"));
			WebElement ex = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.userper.orderticket")));
			ex.click();

			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.userper.sub"));
			WebElement ex1 = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.userper.sub")));
			ex1.click();

			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.userper.read"));
			WebElement ex2 = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.userper.read")));
			ex2.click();

			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.userper.write"));
			WebElement ex3 = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.userper.write")));
			ex3.click();

			Thread.sleep(4000);
			System.out.println(this.objectrepo.get("cbank.master.userper.save"));
			WebElement ex4 = this.driver.findElement(By.id(this.objectrepo.get("cbank.master.userper.save")));
			ex4.click();

		} catch (Exception ex) {
			Assert.fail("Unable to enable permission to Users.");
			ex.printStackTrace();

		}

	}

}
