package com.sample.producer.kafka;
//import org.apache.log4j.Logger;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class MainProducerKafka {

	private MainProducerKafka() { }
	private static long startTime;
	private static long stopTime;
	
	public static void main(final String... args) {
		
		 @SuppressWarnings("resource")
			final AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

			// define the range 
	        int max = 40; 
	        int min = 10; 
	        int range = max - min + 1; 
			
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
			kafkaTemplate.setDefaultTopic("si.test.queue");

			String payload = "";
			for (int i = 0; i < 5000; i++) {
				int rand = (int)(Math.random() * range) + min; 
				payload = "{\"contextElement\":{\"type\" : \"Termometro\",\"id\" : \"" + 1
						+ "\",\"attributes\" : [{ \"name\" : \"temperature\",\"type\" : \"Double\",\"value\" : \""
						+ rand + "\"}]}}";
				kafkaTemplate.sendDefault(payload);
			}
			context.registerShutdownHook();
  		
	}
}