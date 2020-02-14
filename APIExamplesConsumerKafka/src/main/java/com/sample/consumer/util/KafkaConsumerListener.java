package com.sample.consumer.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

public class KafkaConsumerListener implements MessageListener<Integer, String> {

	public void onMessage(ConsumerRecord<Integer, String> record) {
		// TODO Auto-generated method stub
     /* String  topic     = record.topic();
        Integer key       = record.key();
        String  value     = record.value();
        long    offset    = record.offset();
        int     partition = record.partition();*/
		String data=record.value();
		System.out.println(data);
	}
                
}