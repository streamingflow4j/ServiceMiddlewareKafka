package com.service.middleware.listener;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import com.service.middleware.cep.handler.MonitorEventHandler;
import com.service.middleware.model.Entity;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaConsumerListener {

	@Autowired
	private MonitorEventHandler monitorEventHandler;
	
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumerListener.class);
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	@Value("${queue.rule.cep}")
	private String topicNameQueue;

	@Value("${queue.streaming.data}")
	private String topicNameStreaming;

	@KafkaListener(topics = "${queue.rule.cep}", groupId = "1")
	public void consumeRule(ConsumerRecord<String, String> payload){
		log.info("Tópico: {}", topicNameQueue);
		log.info("key: {}", payload.key());
		log.info("Headers: {}", payload.headers());
		log.info("Partion: {}", payload.partition());
		log.info("Order: {}", payload.value());
		String jasonInStr = new String(payload.value());
		try {
			toModel(jasonInStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@KafkaListener(topics = "${queue.streaming.data}", groupId = "0")
	public void consumeStreaming(ConsumerRecord<String, String> payload){
		log.info("Tópico: {}", topicNameStreaming);
		log.info("key: {}", payload.key());
		log.info("Headers: {}", payload.headers());
		log.info("Partion: {}", payload.partition());
		log.info("Order: {}", payload.value());
		String jasonInStr = new String(payload.value());
		try {
			fireEvent(jasonInStr);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void fireEvent(String payload) throws NumberFormatException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			Entity event = objectMapper.readValue(payload, Entity.class);
			monitorEventHandler.handleEntity(event);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error! ===>> "+ e);
		}

	}

	public Entity toModel(String payload) throws IOException{
		Entity myEntity = objectMapper.readValue(payload, Entity.class);
		try {
			monitorEventHandler.createRequestMonitorExpression(myEntity);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error! ===>> "+ e);
		}

		return myEntity;
	}

}