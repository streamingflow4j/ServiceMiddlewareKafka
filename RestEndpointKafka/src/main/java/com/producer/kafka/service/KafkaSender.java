package com.producer.kafka.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.kafka.model.Entity;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class KafkaSender {
	
	@Autowired
	private KafkaTemplate<String, String>  kafkaTemplate;
	
	private static final String QUEUE_DATA = "si.queue.teste";
	
	private static final String QUEUE_RULE = "si.ceprule.queue";

	private ObjectMapper objectMapper = new ObjectMapper();
	private String payload = "";
	
	public void sendData(Entity entity) throws JsonProcessingException {
		this.send(entity,QUEUE_DATA); 
	}	
	
    public void sendRule(Entity entity) throws JsonProcessingException {
    	this.send(entity,QUEUE_RULE);    
	}
    
    public void send(Entity entity, String queue) throws JsonProcessingException {
    	
		payload = objectMapper.writeValueAsString(entity);
		kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
		kafkaTemplate.setDefaultTopic(queue);
		kafkaTemplate.send(new GenericMessage<>(payload));

		System.out.println("Sent msg = " + payload.toString());
	    
	}

}