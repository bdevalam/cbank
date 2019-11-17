package master.cbank.pages;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import master.cbank.lib.PageUtils;
import master.cbank.utils.ReadProperties;

public class DashBoardPage {
	WebDriver driver;
	PageUtils page;
	HashMap<String, String> properties = new HashMap<String, String>();
	HashMap<String, String> objectrepo = new HashMap<String, String>();
  /***
   * this method is constructor of this page.
   * 
   * 
   * @param driver
   * @throws IOException
   */
	
	
	public DashBoardPage(WebDriver driver) throws IOException {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		page = new PageUtils();
		properties = ReadProperties.readProperties();
		objectrepo = ReadProperties.readObjectRepo();

	}

	/**************************************************************************
	 * This method intend to is to click on the expand button in the dashboard.
	 * clickonexpand will click on expand button.
	 * 
	 * @return
	 * 
	 * 
	 *************************************************************************/

	public void clickOnExpand() {
		

		try {
			Thread.sleep(10000);

			this.driver.findElement(By.xpath(this.objectrepo.get("cbank.master.dashboard.expand"))).click();

		} catch (Exception ex) {

			System.out.print("unable to click on the expand button");
			Assert.fail("Unable to click on expand button");
			ex.printStackTrace();
		}
	}

	/**************************************************
	 * This method intend to take text from the element
	 * 
	 * ********************************
	 */

	public String getMessage() {
		String sMessage = null;
		String sMessage1 = null;
		try {
			Thread.sleep(4000);
			// getting the part message/
			sMessage = page.getPartMessge(this.driver, this.objectrepo.get("cbank.master.dashboard.msg1"));
			Thread.sleep(4000);
			sMessage1 = page.getPartMessge(this.driver, this.objectrepo.get("cbank.master.dashboard.msg2"));

		} catch (Exception ex) {
			Assert.fail("unable to get the message");
			ex.printStackTrace();
		}
		return sMessage + sMessage1;

	}

}
