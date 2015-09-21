package com.kpinfotech.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class XmlItem implements Serializable {

	private String id, name, cost, description;

	public XmlItem(String id, String name, String cost, String description) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.description = description;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}