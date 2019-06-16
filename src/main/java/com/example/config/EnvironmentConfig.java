package com.example.config;

import java.util.Properties;

import com.example.common.Constants;
import com.example.util.CommonUtil;



public class EnvironmentConfig {
	private static Properties properties = null;
	public static String OUTPUT_FILE_PATH;
	public static String TOPIC_NAME;

	static {
		properties = CommonUtil.getConfigPropertiesFn();
		loadEnvironmentVariable();
	}

	public static void loadEnvironmentVariable() {
		OUTPUT_FILE_PATH = properties.getProperty(Constants.OUTPUT_FILE_PATH);
		TOPIC_NAME = properties.getProperty(Constants.TOPIC_NAME);
	}
}
