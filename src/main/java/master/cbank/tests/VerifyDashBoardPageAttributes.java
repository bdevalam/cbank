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
import master.cbank.pages.LoginPage;
import master.cbank.utils.*;

public class VerifyDashBoardPageAttributes extends SetUp{
	
	 WebDriver driver;
	 ExtentReports extent;
	 ExtentTest logger;
	 LoginPage login;
	 DashBoardPage dashboard;
	 ReadInputFiles readFiles;
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
  public void sVerifyDashBoardMessage() throws Exception {
	logger = extent.createTest("started the test");
	logger.log(Status.INFO, "Starting the Login verification test");
	try {
		login = new LoginPage(driver);
		login.Login();
		Thread.sleep(2000);
		dashboard = new DashBoardPage(driver);
		dashboard.clickOnExpand();
		Thread.sleep(2000);
	//	String str1 = dashboard.getMessage();
		//String str2 = dashboard.getMessage2();
		String sActualValue = dashboard.getMessage();
		String[] aActualvalue = sActualValue.split(" ");
		//System.out.println(sActualValue);
		readFiles = new ReadInputFiles();
		String[] aExpectedValue = readFiles.readFile();
		
		
		Assert.assertEquals(aActualvalue, aExpectedValue,"Verfiying the String value.");
		logger.log(Status.PASS, "Verfiying the String value.");
		
		
		
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
