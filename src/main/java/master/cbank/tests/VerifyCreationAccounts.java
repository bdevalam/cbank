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
import master.cbank.pages.AccountsPage;
import master.cbank.pages.DashBoardPage;
import master.cbank.pages.GmailPage;
import master.cbank.pages.LoginPage;
import master.cbank.pages.UsersPage;
import master.cbank.utils.*;

public class VerifyCreationAccounts extends SetUp{
	
	 WebDriver driver;
		ExtentHtmlReporter htmlreporter;
		ExtentReports reporter;
		ExtentTest logger;;
	 LoginPage login;
	 DashBoardPage dashboard;
	 ReadInputFiles readFiles;
	 UsersPage users;
	 GmailPage gpage;
	 AccountsPage accnt;
	static HashMap<String, String> properties = new HashMap<String, String>();
	static HashMap<String, String> objectRepo = new HashMap<String, String>();
  
  @BeforeTest
  public void beforeClass() throws IOException {
	  driver = driver_initize();
	  properties = ReadProperties.readProperties();
	  objectRepo = ReadProperties.readObjectRepo();
	  reporter = ExtentManager.getInstance();
	  driver.get(properties.get("url"));

  }
  
  @Test(priority=0)
  public void sVerifycreationofAccounts() throws Exception {
	logger = reporter.createTest("started the Verifying the account test.");
	logger.log(Status.INFO, "Starting the Login verification test");
	try {
		login = new LoginPage(driver);
		logger.log(Status.INFO, "Instantiating the Login  page.");
		login.Login();
		
		Thread.sleep(8000);	
		logger.log(Status.INFO, "Navigating to the Accounts Page");
		accnt = new AccountsPage(driver);
		
		String str = accnt.NavigateToAccounts();
		Assert.assertEquals(str, "Manage Account Nicknames");
		logger.log(Status.PASS, "Verifying  the Accounts Page");
		
		String sActualValue = accnt.createAccount();
		Assert.assertEquals(sActualValue, "The account is created.");
		logger.log(Status.PASS, "Verifying  the Accounts is created");
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
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
		tearDown();
  }

}
