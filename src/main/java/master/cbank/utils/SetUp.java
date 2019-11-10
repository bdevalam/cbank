package master.cbank.utils;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SetUp {
	 static WebDriver driver;
	static HashMap<String, String> properties = new HashMap<String, String>();
	ReadProperties read;
	String sBasedir = System.getProperty("user.dir");

	/**************************************************
	 * @author bhanu 
	 * Initlizing the driver.
	 * 
	 * @return webdriver
	 **************************************************/
	public WebDriver driver_initize() {
		try {
			
			//read = new ReadProperties();
			properties = ReadProperties.readProperties();
			System.out.println(properties.get("browser"));
			String sBrowserType = properties.get("browser");
			// setting up the properties
			if (sBrowserType.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						sBasedir + "\\src\\main\\java\\resources\\geckodriver.exe");
				// instantiating
				this.driver = new FirefoxDriver();
				// deleting cookies
			} else if (sBrowserType.equalsIgnoreCase("chrome")) {
				
				String sFilePath= sBasedir+"\\src\\main\\java\\resources\\chromedriver.exe";
				System.out.println(sFilePath);
				System.setProperty("webdriver.chrome.driver", sFilePath);
				// instantiating
				this.driver = new ChromeDriver();
				
			} else {
				this.driver = new InternetExplorerDriver();
			}
			// Delete all Cookies
			this.driver.manage().deleteAllCookies();
			// maximise the driver
			this.driver.manage().window().maximize();

		} catch (Exception ex) {
			System.out.println("Unable to instantiate the driver");
		}
		return this.driver;
	}

	/**********************************************
	 * @author : Bhanu
	 * 
	 * teardown method will close the browser.
	 **********************************************/
	public void tearDown() {
		driver.close();
	}

}
