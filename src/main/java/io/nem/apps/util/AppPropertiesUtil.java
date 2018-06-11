package io.nem.apps.util;

import java.io.IOException;
import java.util.Properties;




/**
 * The Class AppPropertiesUtil.
 */
public class AppPropertiesUtil {

	/** The properties. */
	static Properties properties = new Properties();
	static {
		try {
			properties.load(AppPropertiesUtil.class.getClassLoader().getResourceAsStream("app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the property.
	 *
	 * @param key
	 *            the key
	 * @return the property
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
