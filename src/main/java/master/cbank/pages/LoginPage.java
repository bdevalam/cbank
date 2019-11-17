package master.cbank.pages;

import java.io.IOException;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import master.cbank.lib.PageUtils;
import master.cbank.utils.ExtentManager;
import master.cbank.utils.ReadInputFiles;
import master.cbank.utils.ReadProperties;

public class LoginPage {

	WebDriver driver;
	PageUtils page;
	ReadInputFiles readfiles;
	HashMap<String, String> properties = new HashMap<String, String>();
	HashMap<String, String> objectrepo = new HashMap<String, String>();
	JSONObject jsonObject;
	ExtentReports extent;
	ExtentTest logger;
/*********************************************
 * This is constructor which will instantiate the 
 * driver.
 * @author bhanu
 * 
 *********************************************/
	public LoginPage(WebDriver driver) throws IOException {

		this.driver = driver;
		properties = ReadProperties.readProperties();
		page = new PageUtils();
		readfiles = new ReadInputFiles();
		properties = ReadProperties.readProperties();
		objectrepo = ReadProperties.readObjectRepo();
		jsonObject = readfiles.readJsonFile();
		extent = ExtentManager.getInstance();
		// logger = extent.createTest("logging into application .");

	}

	/**************************************************************
	 * This method will do login to page with Username and Password
	 * @user  Bhanu
	 * @throws Exception
	 * @return Webdriver
	 * @parameters None
	 **************************************************************/

	public WebDriver Login() throws Exception {
		try {
            //Finding the username Element and input the username
			this.driver.findElement(By.id(objectrepo.get("cbank.master.login.username")))
					.sendKeys(properties.get("sUserName"));
			
			//Finding the password Element and input the password
			this.driver.findElement(By.id(objectrepo.get("cbank.master.login.password")))
					.sendKeys(properties.get("sPasswd"));
			
			//Finding the save Element and click on the save button
			this.driver.findElement(By.xpath("//button[@type='submit']")).click();

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("unable to complete login");

		}
		return this.driver;
	}

	/***************************************************************
	 * This method will do logout in Home Page
	 * 
	 * @return 
	 * @throws Exception
	 * @username bhanu
	 * 
	 **************************************************************/

	public void Logout() throws Exception {
		try {
			//Finding the profile Element and  clicking on the element
			this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.global.profile"))).click();
			//Finding the name and clicking on the elemment.
			this.driver.findElement(By.id(this.objectrepo.get("cbank.master.global.singout"))).click();

		} catch (Exception ex) {
			Assert.fail("unable to complete logout");
		}
	}

}
