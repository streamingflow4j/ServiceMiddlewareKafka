package com.streamingflow.main;


import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class UpdateRule {

	private UpdateRule() { }

public static void main(final String... args) throws InterruptedException {
        

		@SuppressWarnings("resource")
		final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
		kafkaTemplate.setDefaultTopic("si.ceprule.queue");
	   
	    String payload = "";
	    payload = "{\"contextElement\":{"
	    				+ "\"type\" : \"EDIT_RULECEP\","
	    		        + "\"id\" : \"Rule3\","
			    		+ "\"attributes\" : [{ \"name\"  : \"RULE_ID\","
		                + "\"type\"  : \"String\","
		                + "\"value\" : \"f9c9c1c1-d1ed-4f03-99cb-7083533ac2e0\"}"
		                + "{\"name\"  : \"RULE\","
			            + "\"type\"  : \"String\","
			            + "\"value\" : \"select temperature from Termometer.win:time(10 sec)\"}"
			            + "{\"name\"  : \"QUEUE_1\","
    		            + "\"type\"  : \"QUEUE\","
    		            + "\"value\" : \"si.cep.queue\"}"
	                    + "]}}";
	
	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
	}
}
