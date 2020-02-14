package com.thesis.layer.service.bean;

import java.io.Serializable;
import java.util.List;

public class Entity implements Serializable{
	 String id;
	 String type;
	 List<Attributes> attributes;

	public Entity() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<Attributes> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attributes> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
