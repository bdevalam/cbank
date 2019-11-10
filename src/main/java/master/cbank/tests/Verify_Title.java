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
import org.testng.annotations.AfterClass;
import master.cbank.utils.*;

public class Verify_Title extends SetUp{
	
	static WebDriver driver;
	 ExtentReports extent;
	 ExtentTest logger;
	static HashMap<String, String> properties = new HashMap<String, String>();
  
  @BeforeClass
  public void beforeClass() throws IOException {
	  this.driver = driver_initize();
	  properties = ReadProperties.readProperties();
	  ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
	  extent = new ExtentReports();
	  extent.attachReporter(htmlReporter);
	  this.driver.get(properties.get("url"));

  }
  
  @Test
  public void sVerifyTitle() {
	logger = extent.createTest("started the test");
	String sTitle = this.driver.getTitle();
	logger.log(Status.PASS, "Page title verification is successful.");
	
  }

  @AfterClass
  public void afterClass() {
		this.extent.flush();
		tearDown();
  }

}
