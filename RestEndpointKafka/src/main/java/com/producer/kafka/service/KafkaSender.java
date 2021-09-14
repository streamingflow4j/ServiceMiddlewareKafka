package com.producer.kafka.service;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.kafka.model.Entity;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class KafkaSender {
	
	@Autowired
	private KafkaTemplate<String, String>  kafkaTemplate;

	private ObjectMapper objectMapper = new ObjectMapper();
	
	private static final Logger log = LoggerFactory.getLogger(KafkaSender.class);
	
	private String payload = "";
	
	private final Environment env;
	
	public KafkaSender(Environment env) {
		this.env = env;
	}
	
	public void sendData(Entity entity) throws JsonProcessingException {
		this.send(entity,getQUEUE_DATA()); 
	}	
	
    public void sendRule(Entity entity) throws JsonProcessingException {
    	this.send(entity,getQUEUE_RULE());    
	}
    
    public void send(Entity entity, String queue) throws JsonProcessingException {
    	
		payload = objectMapper.writeValueAsString(entity);
		kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
		kafkaTemplate.setDefaultTopic(queue);
		kafkaTemplate.send(new GenericMessage<>(payload));
		
		log.info("Sent msg: {}" , payload.toString());

	    
	}
    
    public String load(String propertyName) { 
		return env.getRequiredProperty(propertyName); 
	}
    
    public String getQUEUE_DATA() {
		return load("queue.streaming.data");
		
	}

	public String getQUEUE_RULE() {
		return load("queue.rule.cep");		
	}

}