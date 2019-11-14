package master.cbank.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ReadInputFiles {
	static String baseDir = System.getProperty("user.dir");
	static String inputfilename = baseDir + "\\src\\main\\java\\master\\cbank\\data\\input_en.txt";
	static String inputjsonfile = baseDir + "\\src\\main\\java\\master\\cbank\\data\\input.json";
	//static HashMap<String, String> input = new HashMap<String, String>();
	static Map<String, Object> input = new HashMap<String,Object>();
	String[] lst = null ;
	public String[] readFile() {
		try {
			
			File file = new File(inputfilename);			  
			 BufferedReader br = new BufferedReader(new FileReader(file)); 
			  int c=1;
			  String st; 
			  while ((st = br.readLine()) != null) {
			   // System.out.println(st);			
			    lst = st.split(" ");			   
			    System.out.println(lst.length);
		  } 	
	}
	catch(Exception ex){
		System.out.println("unable to read the file");

	} return lst;

	}
	
	@SuppressWarnings("unchecked")
	public JSONObject readJsonFile() {
		 JSONParser parser = new JSONParser();
		 JSONObject jsonObject = null ;
	        try {
 
	        	Reader reader = new FileReader(inputjsonfile);

	            jsonObject = (JSONObject) parser.parse(reader);
	           /* System.out.println(jsonObject);
	            
	            String name = (String) jsonObject.get("Usesrname");
	            System.out.println(name);

	            String language = (String) jsonObject.get("language");
	            System.out.println(language);
*/
	          

	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return jsonObject;
	    }


		
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadInputFiles RF = new ReadInputFiles();
		String[] aExpectedValue =RF.readFile();
		RF.readJsonFile();
		
		//for(String str21: aExpectedValue) {
		//	System.out.println(str21);
			
		//}
		
	}

}
