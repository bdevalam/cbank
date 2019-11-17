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
import master.cbank.pages.DashBoardPage;
import master.cbank.pages.GmailPage;
import master.cbank.pages.LoginPage;
import master.cbank.pages.UsersPage;
import master.cbank.utils.*;

public class VerifyCreatingusers extends SetUp {

	WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports reporter;
	ExtentTest logger;
	LoginPage login;
	DashBoardPage dashboard;
	ReadInputFiles readFiles;
	UsersPage users;
	GmailPage gpage;
	static HashMap<String, String> properties = new HashMap<String, String>();
	static HashMap<String, String> objectRepo = new HashMap<String, String>();

	@BeforeTest
	public void beforeClass() throws IOException {
		driver = driver_initize();
		properties = ReadProperties.readProperties();
		objectRepo = ReadProperties.readObjectRepo();
		reporter = ExtentManager.getInstance();
		logger = reporter.createTest("Verify creating users and assign the permissions.");
		driver.get(properties.get("url"));
		logger.log(Status.INFO, "Page Launched successfully");
	}

	@Test(priority = 1)
	public void sVerifycreationofUsers() throws Exception {
		
		logger.log(Status.INFO, "Starting the Login verification test");
		try {
			login = new LoginPage(driver);
			logger.log(Status.INFO, "Calling the Login Page.");
			login.Login();
			Thread.sleep(8000);
			logger.log(Status.INFO, "Instantiating the User page.");
			users = new UsersPage(driver);
			Thread.sleep(4000);
			logger.log(Status.INFO, "Navigating to the Users Page");
			String sActualMsg1= users.NavigateToUsers();
			Assert.assertEquals(sActualMsg1, "User Management");
			logger.log(Status.PASS, "Navigating to the Users Page is successful.");
			Thread.sleep(4000);
			logger.log(Status.INFO, "creating the user.");
			String sActualvalue= users.createUser();
			Assert.assertEquals(sActualvalue, "Permissions");
			logger.log(Status.PASS, "User Creation is passed");
		
			Thread.sleep(4000);
			logger.log(Status.INFO, "Logging out from UI.");
			login.Logout();
			Thread.sleep(4000);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test(priority = 2, enabled = false)
	public void sVerifyActivation() throws Exception {
		logger.log(Status.INFO, "Starting the Login to Gmail verification test");
		try {
			logger.log(Status.INFO, "Instantiating to Gmail.");
			gpage = new GmailPage(driver);
			logger.log(Status.INFO, "Verifying the email");
			gpage.verifyEmail();
			logger.log(Status.PASS, "Verifying the email is passed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 3)
	public void sAssignpersmission() throws Exception {
		logger.log(Status.INFO, "Starting the Login verification test");
		try {
			login = new LoginPage(driver);
			logger.log(Status.INFO, "Calling the Login Page.");
			login.Login();
			Thread.sleep(8000);
			logger.log(Status.INFO, "Instantiating the User page.");
			users = new UsersPage(driver);
			Thread.sleep(4000);
			logger.log(Status.INFO, "Navigating to the Users Page");
			String sActualMsg1= users.NavigateToUsers();
			Assert.assertEquals(sActualMsg1, "User Management");
			
			logger.log(Status.PASS, "Navigating to the Users Page is successful.");
			Thread.sleep(4000);
			users.assignPermissions();
			logger.log(Status.PASS, "Assinging the permissions is passed.");
			Thread.sleep(4000);
			login.Logout();
			Thread.sleep(4000);

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
		tearDown();
		logger.log(Status.PASS, "browser is closed.");
		this.reporter.flush();
	}

}
