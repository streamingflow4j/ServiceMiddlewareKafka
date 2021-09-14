package com.example.consumer.kafka.listener;




import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TopicListener {
	
	 	@Value("${topic.name.consumer")
	    private String topicName;

	 	Logger log = LoggerFactory.getLogger(TopicListener.class);
	 	
	    @KafkaListener(topics = "${topic.name.consumer}", groupId = "0")
	    public void consume(ConsumerRecord<String, String> payload){
	    	
	        log.info("Tópico: {}", topicName);
	        log.info("key: {}", payload.key());
	        log.info("Headers: {}", payload.headers());
	        log.info("Partion: {}", payload.partition());
	        log.info("Order: {}", payload.value());
	    }
}
