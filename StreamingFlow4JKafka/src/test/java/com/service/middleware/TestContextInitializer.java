package com.service.middleware;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class TestContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.setProperty("KAFKA_RULE_QUEUE","si.ceprule.queue");
        System.setProperty("KAFKA_STREAMING_QUEUE","si.test.queue");
        System.setProperty("KAFKA_BROKERCONNECT","localhost:9092");
    }
}
