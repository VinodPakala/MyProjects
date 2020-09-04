package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataProvider {

	Properties pro;
	
	public ConfigDataProvider() {
		
		File src= new File("./Config/config.properties");
		try {
			FileInputStream config = new FileInputStream(src);
			pro = new Properties();
			pro.load(config);
		} catch (Exception e) {
			System.out.println("not able to load properties file>> "+ e.getMessage());
		} 
	}

	public String getDataFromConfig(String keyToSearch) {
	return	pro.getProperty(keyToSearch);
	}
	public String getBrowser() {
	return	pro.getProperty("Browser");
	}
	
	public String getQaUrl() {
	return	pro.getProperty("QaUrl");
	}
	
}
