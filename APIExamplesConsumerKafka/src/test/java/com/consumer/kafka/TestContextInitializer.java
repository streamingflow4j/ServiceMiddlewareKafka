package com.consumer.kafka;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class TestContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.setProperty("KAFKA_CONSUMER_QUEUE","si.cep.queue");
        System.setProperty("KAFKA_BROKERCONNECT","localhost:9092");
    }
}
