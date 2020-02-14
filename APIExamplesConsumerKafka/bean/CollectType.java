package com.thesis.layer.service.bean;

public enum CollectType{ 
	
	NONE ("NONE"),
	ADD_EVENT_TYPE("ADD_EVENT"),
	ADD_RULE_TYPE("RULECEP"), 
	ADD_RULE_ATTR_NAME ("RULE"),
	ADD_RULE_ATTR_QUEUE ("QUEUE"),
	DEL_RULE_ATTR_ID ("RULE_ID"),
	DEL_RULE_TYPE ("DEL_RULE");
	
	
	private String name;

	CollectType(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static CollectType getEnumFromString(String value){
		if(value != null){
			for(CollectType type : CollectType.values()){
				if(value.equalsIgnoreCase(type.name)){
					return type;
				}
			}
		}
		return null;
	}
};