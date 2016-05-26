package com.shivam.sensorapi.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity("stocks")
public class Stock {
	@Id
	@JsonIgnore
	private ObjectId id;
	private String value;
	private Long timeStamp;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "Metric [id=" + id + ", value=" + value + ", timestamp=" + timeStamp + "]";
	}
	
}
