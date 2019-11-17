package master.cbank.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import master.cbank.pages.LoginPage;
import master.cbank.utils.*;


public class Verify_Title extends SetUp{

	  WebDriver driver;
		 ExtentHtmlReporter htmlreporter;
	     ExtentReports reporter;
	     ExtentTest logger;
	 LoginPage login;
	static HashMap<String, String> properties = new HashMap<String, String>();
  
  @BeforeTest
  public void beforeClass() throws IOException {
	  driver = driver_initize();
	  properties = ReadProperties.readProperties();
	  reporter = ExtentManager.getInstance();
	  logger = reporter.createTest("Verify the title of page.");  
	  driver.get(properties.get("url"));
	  logger.log(Status.INFO, "Page Launched successfully");
	  
	  

  }
  
  @Test(priority=0)
  public void sVerifyTitle() {
	
	String sTitle = driver.getTitle();
	Assert.assertEquals(sTitle, "Sign In | ClickBank");
	logger.log(Status.PASS, "Page title verification is successful.");
	
  }
  
  @AfterMethod
	 public void getResult(ITestResult result){
	 if(result.getStatus() == ITestResult.FAILURE){
	 
	 
	 logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
	 logger.log(Status.FAIL, "Test Case Failed is "+result.getThrowable());
	 }else if(result.getStatus() == ITestResult.SKIP){
	 logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
	 }
	}
  @AfterTest
  public void afterClass() {
		this.reporter.flush();
		logger.log(Status.INFO, "closing the browser.");
		tearDown();
  }
}
