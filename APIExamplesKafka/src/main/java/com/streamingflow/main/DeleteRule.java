package com.streamingflow.main;


import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class DeleteRule {
     
	private DeleteRule() { }

public static void main(final String... args) throws InterruptedException {
        

		
		@SuppressWarnings("resource")
		final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
		kafkaTemplate.setDefaultTopic("si.ceprule.queue");
	   

	    String payload = "";
	    payload = "{\"contextElement\":{\"type\" : \"DEL_RULE\",\"id\" : \"DelRule3\","
	    		+ "\"attributes\" : [{ \"name\"  : \"RULE_ID\","
	    		                    + "\"type\"  : \"String\","
	    		                    + "\"value\" : \"d081f1bc-4d68-4427-bd47-59684e480327\"}"
	    		                    + "]}}";
	
	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
	}
}
