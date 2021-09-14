package com.service.middleware.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public final class MainServiceMiddlewareKafka {
	
	private static final Logger log = LoggerFactory.getLogger(MainServiceMiddlewareKafka.class);


	public static void main(final String... args) throws Exception {
		@SuppressWarnings("resource")
		final AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		context.registerShutdownHook();
		//Restore all stored rules when application restart
		log.info("Service started: {}" , MainServiceMiddlewareKafka.class.getName());
	}

}