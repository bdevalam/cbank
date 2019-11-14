package master.cbank.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import master.cbank.utils.SetUp;


import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import master.cbank.pages.DashBoardPage;
import master.cbank.pages.GmailPage;
import master.cbank.pages.LoginPage;
import master.cbank.pages.UsersPage;
import master.cbank.utils.*;

public class VerifyCreatingusers extends SetUp{
	
	 WebDriver driver;
	 ExtentReports extent;
	 ExtentTest logger;
	 LoginPage login;
	 DashBoardPage dashboard;
	 ReadInputFiles readFiles;
	 UsersPage users;
	 GmailPage gpage;
	static HashMap<String, String> properties = new HashMap<String, String>();
	static HashMap<String, String> objectRepo = new HashMap<String, String>();
  
  @BeforeClass
  public void beforeClass() throws IOException {
	  driver = driver_initize();
	  properties = ReadProperties.readProperties();
	  objectRepo = ReadProperties.readObjectRepo();
	  ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
	  extent = new ExtentReports();
	  extent.attachReporter(htmlReporter);
	  driver.get(properties.get("url"));

  }
  
  
  
  @Test(priority=0)
  public void sVerifycreationofUsers() throws Exception {
	logger = extent.createTest("started the test");
	logger.log(Status.INFO, "Starting the Login verification test");
	try {
		login = new LoginPage(driver);
		login.Login();
		Thread.sleep(8000);	
		users = new UsersPage(driver);
		Thread.sleep(4000);
		users.NavigateToUsers();
		Thread.sleep(4000);
		users.createUsers();
		Thread.sleep(4000);
		
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  }


  @Test(priority=0)
  public void sVerifyActivation() throws Exception {
	logger = extent.createTest("started the test");
	logger.log(Status.INFO, "Starting the Login verification test");
	try {
		
		gpage = new GmailPage(driver);
		
		gpage.verifyEmail();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  }
  
  
  @AfterClass
  public void afterClass() {
		this.extent.flush();
		tearDown();
  }

}
