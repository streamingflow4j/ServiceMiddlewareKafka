package com.service.middleware.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderSupport;

public class AppConfigProperties {

	private static AppConfigProperties myObj;
	private static Properties properties = new Properties();
	private static final AbstractApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath:applicationContext.xml");

	/**
	 * Create private constructor
	 */
	private AppConfigProperties() {

	}

	/**
	 * Create a static method to get instance.
	 */
	public static AppConfigProperties getInstance() {
		if (myObj == null) {
			myObj = new AppConfigProperties();
		}
		return myObj;
	}

	public String getProperties(String nameProp) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (properties.isEmpty()) {
			String[] postProcessorNames = context.getBeanNamesForType(BeanFactoryPostProcessor.class, true, true);
			for (String ppName : postProcessorNames) {
				BeanFactoryPostProcessor beanProcessor = context.getBean(ppName, BeanFactoryPostProcessor.class);
				if (beanProcessor instanceof PropertyResourceConfigurer) {
					PropertyResourceConfigurer propertyResourceConfigurer = (PropertyResourceConfigurer) beanProcessor;
					Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
					mergeProperties.setAccessible(true);
					Properties props = (Properties) mergeProperties.invoke(propertyResourceConfigurer);
					Method convertProperties = PropertyResourceConfigurer.class.getDeclaredMethod("convertProperties",
							Properties.class);
					convertProperties.setAccessible(true);
					convertProperties.invoke(propertyResourceConfigurer, props);
					properties.putAll(props);
				}
			}
		}
		return properties.getProperty(nameProp);

	}
}