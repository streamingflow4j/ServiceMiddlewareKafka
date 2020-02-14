package com.sample.consumer.kafkacep;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public final class MainConsumerKafka {

	private MainConsumerKafka() { }
	
	public static void main(final String... args) {

		@SuppressWarnings("resource")
		final AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
 	    System.out.println("------");
 	    
		context.registerShutdownHook();
        System.out.println("ConsumerCepQueue");
        
	}
}

