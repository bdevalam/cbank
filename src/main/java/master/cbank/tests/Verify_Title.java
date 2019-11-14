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

import master.cbank.pages.LoginPage;
import master.cbank.utils.*;

public class Verify_Title extends SetUp{
	
	  WebDriver driver;
	 ExtentReports extent;
	 ExtentTest logger;
	 LoginPage login;
	static HashMap<String, String> properties = new HashMap<String, String>();
  
  @BeforeClass
  public void beforeClass() throws IOException {
	  driver = driver_initize();
	  properties = ReadProperties.readProperties();
	  ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
	  extent = new ExtentReports();
	  extent.attachReporter(htmlReporter);
	  driver.get(properties.get("url"));

  }
  
  @Test(priority=0)
  public void sVerifyTitle() {
	logger = extent.createTest("started the test");
	String sTitle = driver.getTitle();
	Assert.assertEquals(sTitle, "Sign In | ClickBank");
	logger.log(Status.PASS, "Page title verification is successful.");
	
  }
  
  @Test(priority=1)
  public void sVerifyLogin() throws Exception {
    
	logger.log(Status.INFO, "Starting the Login verification test");
	try {
		login = new LoginPage(driver);
		login.Login();
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
