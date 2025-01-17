package master.cbank.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class ReadProperties {
	static String baseDir= System.getProperty("user.dir");
	static String configfilename = baseDir+"\\src\\main\\java\\master\\cbank\\data\\config.properties";
	static String objectfilename = baseDir+"\\src\\main\\java\\master\\cbank\\data\\objectrepo.properties";
	
	
	static Properties prop = new Properties();
	static Properties objectrepo = new Properties();
	static HashMap<String, String> properties = new HashMap<String, String>();
	static HashMap<String, String> ElementObject = new HashMap<String, String>();

	public static HashMap<String, String> readProperties() throws IOException {

		try {
			prop.load(new FileInputStream(configfilename));
			// prop.load(inStream);
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				properties.put(key, value);
			}
			//System.out.println(properties);
		} catch (Exception e) {
			System.out.println("Unable to iterate the confing properties. ");
		}
		return properties;
		
	}

	public static HashMap<String, String> readObjectRepo() throws IOException {

		try {
			objectrepo.load(new FileInputStream(objectfilename));
			Enumeration<?> e = objectrepo.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = objectrepo.getProperty(key);
				ElementObject.put(key, value);
			}
			System.out.println(ElementObject);
		} catch (Exception e) {
			System.out.println("Unable to iterate the objectrepo properties. ");
		}
		return ElementObject;
	}
/*	
	public static void main(String[] args) throws IOException {
		
		System.out.println(System.getProperty("user.dir"));
		ReadProperties rp = new ReadProperties();
		ReadProperties.readProperties();
		ReadProperties.readObjectRepo();
	}
*/	
}
