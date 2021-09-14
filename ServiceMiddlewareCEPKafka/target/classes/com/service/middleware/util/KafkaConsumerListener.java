package com.service.middleware.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.service.middleware.cep.handler.MonitorEventHandler;
import com.service.middleware.model.Entity;

public class KafkaConsumerListener implements MessageListener<Integer, String> {

	@Autowired
	private MonitorEventHandler monitorEventHandler;
	
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumerListener.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void onMessage(ConsumerRecord<Integer, String> record) {
		// TODO Auto-generated method stub
		String topicStream = null;
		String topicRule = null;
		try {
			topicRule = AppConfigProperties.getInstance().getProperties("queue.rule.cep");
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			topicStream = AppConfigProperties.getInstance().getProperties("queue.streaming.data");
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (record.topic().equals(topicStream)) {
			try {
				fireEvent(record.value());
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (record.topic().equals(topicRule)) {
			try {
				toModel(record.value());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			log.error("Error! ===>> No event or different queue names.");
		}
	}

	public void fireEvent(@Payload String payload) throws NumberFormatException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			if (monitorEventHandler == null) {
				monitorEventHandler = (MonitorEventHandler) ApplicationContextProvider.getBean("monitorEventHandler");
			}
			Entity event = objectMapper.readValue(payload, Entity.class); 
			monitorEventHandler.handleEntity(event);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error! ===>> "+ e);
		}

	}

	public Entity toModel(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
		Entity myEntity = objectMapper.readValue(payload, Entity.class); 
		try {
			if (monitorEventHandler == null) {
				monitorEventHandler = (MonitorEventHandler) ApplicationContextProvider.getBean("monitorEventHandler");
			}
			monitorEventHandler.createRequestMonitorExpression(myEntity);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error! ===>> "+ e);
		}

		return myEntity;
	}
}