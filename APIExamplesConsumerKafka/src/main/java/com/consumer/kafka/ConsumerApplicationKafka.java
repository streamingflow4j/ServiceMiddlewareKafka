package com.consumer.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@EnableAutoConfiguration
@SpringBootApplication
public class ConsumerApplicationKafka {

	public static void main(String[] args) {
		System.setProperty("server.port", "8082");
		SpringApplication.run(ConsumerApplicationKafka.class, args);		
	}

}
