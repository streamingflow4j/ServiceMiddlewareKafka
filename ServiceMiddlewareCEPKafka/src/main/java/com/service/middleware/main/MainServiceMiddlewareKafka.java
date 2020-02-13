package com.service.middleware.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class MainServiceMiddlewareKafka {

	private MainServiceMiddlewareKafka() {
	}

	public static void main(final String... args) throws Exception {
		@SuppressWarnings("resource")
		final AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		context.registerShutdownHook();
		//Restore all stored rules when application restart
		System.out.println("ServiceMiddlewareCEP_Kafka");
	}

}