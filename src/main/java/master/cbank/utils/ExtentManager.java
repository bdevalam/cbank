package master.cbank.utils;

import java.util.Date;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.sun.jna.Platform;


public class ExtentManager {
	private static ExtentReports extent;
  
    private static String reportFileName = "ExtentReports-Version3-Test-Automaton-Report.html";
  
 //   private static String windowsPath = System.getProperty("user.dir")+ "\\TestReport";
 
 
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
 
    //Create an extent report instance
    public static ExtentReports createInstance() {
       // platform = getCurrentPlatform();
       // String fileName = getReportFileLocation(platform);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportFileName);
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        //htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Testing");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("SampleTest");
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }
 
    

	public static String generateFileName(ITestResult result){
		Date date = new Date();
		String fileName = result.getName()+ "_" + date;
		return fileName;
		
	}
   
}

