package master.cbank.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import master.cbank.utils.SetUp;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import master.cbank.pages.DashBoardPage;
import master.cbank.pages.LoginPage;
import master.cbank.utils.*;

public class VerifyDashBoardPageAttributes extends SetUp {

	WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports reporter;
	ExtentTest logger;
	LoginPage login;
	DashBoardPage dashboard;
	ReadInputFiles readFiles;
	static HashMap<String, String> properties = new HashMap<String, String>();
	static HashMap<String, String> objectRepo = new HashMap<String, String>();

	@BeforeTest
	public void beforeTest() throws IOException {
		driver = driver_initize();
		properties = ReadProperties.readProperties();
		objectRepo = ReadProperties.readObjectRepo();
		reporter = ExtentManager.getInstance();
		
		driver.get(properties.get("url"));

	}

	@Test(priority = 0)
	public void sVerifyDashBoardMessage() throws Exception {
		logger = reporter.createTest("started the verifying the dashboard test.");
		try {
			login = new LoginPage(driver);
			login.Login();
			logger.log(Status.PASS, "Instantiating the Login  page.");
			Thread.sleep(2000);
			dashboard = new DashBoardPage(driver);
			logger.log(Status.PASS, "Instantiating the DashBoard  page.");
		    dashboard.clickOnExpand();
			Thread.sleep(2000);
			String sActualValue = dashboard.getMessage();
			String[] aActualvalue = sActualValue.split(" ");
			readFiles = new ReadInputFiles();
			String[] aExpectedValue = readFiles.readFile();
			Assert.assertEquals(aActualvalue, aExpectedValue, "Verfiying the content value.");
			//logger.log(Status.PASS, "Verfiying the String value.");
			//login.Logout();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, "Test Case Failed is " + result.getName());
			logger.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP, "Test Case Skipped is " + result.getName());
		}
	}

	@AfterTest
	public void afterClass() {
		this.reporter.flush();
		tearDown();
	}

}
