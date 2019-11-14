package master.cbank.pages;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import master.cbank.lib.PageUtils;
import master.cbank.utils.ReadProperties;

public class DashBoardPage {
	WebDriver driver;
	PageUtils page;
	HashMap<String, String> properties = new HashMap<String, String>();
	HashMap<String, String> objectrepo = new HashMap<String, String>();

	public DashBoardPage(WebDriver driver) throws IOException {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		page = new PageUtils();
		properties = ReadProperties.readProperties();
		objectrepo = ReadProperties.readObjectRepo();
		//System.out.println(objectrepo);

	}

	/***
	 * This method intend to is to click on the expand button in the dashboard.
	 * 
	 * 
	 */

	public void clickOnExpand() {

		try {
			Thread.sleep(10000);
			//System.out.println(this.objectrepo.get("cbank.master.dashboard.expand"));
			this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.dashboard.expand"))).click();

		} catch (Exception ex) {

			System.out.print("unable to click on the expand button");
			ex.printStackTrace();
		}
	}

	/***
	 * This method intend to take text from the element
	 * 
	 * ********************************
	 */
/*
	public String getMessage1() {
		String sMessage = null;
		try {
			Thread.sleep(4000);
			sMessage = this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.dashboard.msg1"))).getText();
          //  System.out.println(sMessage);
		} catch (Exception ex) {

			System.out.print("unable to get the message.");
			ex.printStackTrace();
		}
		return sMessage; 
	}

	*/
	
	public String getMessage() {
		String sMessage = null;
		String sMessage1 = null;
		try {
			
			Thread.sleep(4000);
		    sMessage = page.getPartMessge(this.driver,this.objectrepo.get("cbank.master.dashboard.msg1"));
			Thread.sleep(4000);
			sMessage1 = page.getPartMessge(this.driver,this.objectrepo.get("cbank.master.dashboard.msg2"));
		
		}catch(Exception ex) {
			System.out.println("unable to get the message");
		}return sMessage+sMessage1;
		
	}
	
	

}
