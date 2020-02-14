package com.streamingflow.main;


import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class CreateRule {

	private CreateRule() { }

public static void main(final String... args) throws InterruptedException {
        		
		@SuppressWarnings("resource")
		final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    	KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
    	kafkaTemplate.setDefaultTopic("si.ceprule.queue");

	    String payload = "";
  
	    payload = "{\"contextElement\":{\"type\" : \"RULECEP\",\"id\" : \"Rule7\","
	    		+ "\"attributes\" : [{ \"name\"  : \"RULE\","
	    		                    + "\"type\"  : \"String\","
	    		                    + "\"value\" : \"select temperature from Termometer.win:time(5 sec)\"},"
	    		                    + "{\"name\"  : \"QUEUE_1\","
	    	    		            + "\"type\"  : \"QUEUE\","
	    	    		            + "\"value\" : \"si.cep.queue\"}"
	    		                    + "]}}";
	    

	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
	}
}

