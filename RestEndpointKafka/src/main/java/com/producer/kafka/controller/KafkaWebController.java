package com.producer.kafka.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.producer.kafka.model.Entity;
import com.producer.kafka.service.KafkaSender;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(value = "/kafka")
@Log4j2
public class KafkaWebController {


	private final KafkaSender kafkaSender;

    public KafkaWebController(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/data/create")
	public String newData(@RequestBody Entity entity) throws JsonProcessingException {	
		kafkaSender.sendData(entity);
		return "Message sent to the Kafka JavaInUse Successfully";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/event/create")
	public String newEvent(@RequestBody Entity entity) throws JsonProcessingException {
		kafkaSender.sendRule(entity);
		return "Message sent to the Kafka JavaInUse Successfully";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/rule/create")
	public String createRule(@RequestBody Entity entity) throws JsonProcessingException {
		kafkaSender.sendRule(entity);
		return "Message sent to the Kafka JavaInUse Successfully";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/rule/update")
	public String updateRule(@RequestBody Entity entity) throws JsonProcessingException {
		kafkaSender.sendRule(entity);
		return "Message sent to the Kafka JavaInUse Successfully";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/rule/delete")
	public String deleteRule(@RequestBody Entity entity) throws JsonProcessingException {
		kafkaSender.sendRule(entity);
		return "Message sent to the Kafka JavaInUse Successfully";
	}
	
}
