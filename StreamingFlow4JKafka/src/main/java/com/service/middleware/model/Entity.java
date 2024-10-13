package com.service.middleware.model;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Entity {
	
	private String id;
	private String type;
	private List<Attribute> attributes;

	public Entity() {
	}

	public Entity(String type, String id, List<Attribute> attributes) {
		super();
		this.id = id;
		this.type = type;
		this.attributes = attributes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	@JsonCreator
	public static Entity create(String jsonString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Entity entity = null;
		entity = objectMapper.readValue(jsonString, Entity.class);
		return entity;
	}

}
