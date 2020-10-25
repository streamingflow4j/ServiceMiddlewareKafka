package com.producer.kafka.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.producer.kafka.model.Entity;
import com.producer.kafka.service.KafkaSender;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "/kafka")
@Log4j2
public class KafkaWebController {

	@Autowired
	KafkaSender kafkaSender;
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/create/data")
	public String newData(@RequestBody Entity entity) throws JsonProcessingException {	
		kafkaSender.sendData(entity);
		return "Message sent to the Kafka JavaInUse Successfully";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/create/event")
	public String newEvent(@RequestBody Entity entity) throws JsonProcessingException {
		kafkaSender.sendRule(entity);
		return "Message sent to the Kafka JavaInUse Successfully";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/create/rule")
	public String createRule(@RequestBody Entity entity) throws JsonProcessingException {
		kafkaSender.sendRule(entity);
		return "Message sent to the Kafka JavaInUse Successfully";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/update/rule")
	public String updateRule(@RequestBody Entity entity) throws JsonProcessingException {
		kafkaSender.sendRule(entity);
		return "Message sent to the Kafka JavaInUse Successfully";
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/delete/rule")
	public String deleteRule(@RequestBody Entity entity) throws JsonProcessingException {
		kafkaSender.sendRule(entity);
		return "Message sent to the Kafka JavaInUse Successfully";
	}

}
