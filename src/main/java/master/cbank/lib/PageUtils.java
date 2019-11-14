package master.cbank.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageUtils {
       
	public String getPartMessge(WebDriver driver, String str) {
		String sMessage = null;
		try {
			Thread.sleep(4000);
			sMessage = driver.findElement(By.xpath(str)).getText();
          //  System.out.println(sMessage);
		} catch (Exception ex) {

			System.out.print("unable to get the message.");
			ex.printStackTrace();
		}
		return sMessage; 
	}

	


	
	
	
}
