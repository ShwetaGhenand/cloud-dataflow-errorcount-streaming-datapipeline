package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.common.Constants;

public class CommonUtil {
	private static final Logger LOG = LoggerFactory.getLogger(CommonUtil.class);

	private CommonUtil() {

	}

	public static Properties getConfigPropertiesFn() {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream(Constants.APP_CONFIG_FILE);
		try {
			properties.load(is);
		} catch (IOException e) {
			LOG.error("Load properties failed:" + e.getMessage(), e);
		}
		return properties;
	}
}
