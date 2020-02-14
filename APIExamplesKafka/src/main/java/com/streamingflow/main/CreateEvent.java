package com.streamingflow.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class CreateEvent {

	
	private CreateEvent() { }

public static void main(final String... args) throws InterruptedException {
   
		
		@SuppressWarnings("resource")
		final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
		kafkaTemplate.setDefaultTopic("si.ceprule.queue");
	   

	    String payload = "";
	    payload = "{\"contextElement\":{\"type\" : \"ADD_EVENT\",\"id\" : \"Termometer\","
	    		+ "\"attributes\" : [{ \"name\"  : \"id\","
	    		                    + "\"type\"  : \"String\","
	    		                    + "\"value\" : \"0\"}"
	    		                    + "{\"name\" : \"temperature\","
	    	    		            + "\"type\"  : \"Double\","
	    	    		            + "\"value\" : \"0\"}"
	    		                    + "]}}";

	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
	}
}
