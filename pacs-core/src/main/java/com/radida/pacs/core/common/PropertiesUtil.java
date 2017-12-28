package com.radida.pacs.core.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	private Properties prop;

	public void setProperties(String properties) {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(properties);
		prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public String getProperty(String key) {
		if(prop != null) {
			return prop.getProperty(key);
		}
		return "";
	}
}
