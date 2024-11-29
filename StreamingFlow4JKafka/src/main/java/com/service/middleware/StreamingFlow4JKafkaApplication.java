package com.service.middleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class StreamingFlow4JKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingFlow4JKafkaApplication.class, args);
	}

}
