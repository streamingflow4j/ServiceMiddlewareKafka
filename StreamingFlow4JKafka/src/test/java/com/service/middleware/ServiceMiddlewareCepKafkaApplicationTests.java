package com.service.middleware;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(initializers = TestContextInitializer.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceMiddlewareCepKafkaApplicationTests {

	@Test
	void contextLoads() {
	}

}
