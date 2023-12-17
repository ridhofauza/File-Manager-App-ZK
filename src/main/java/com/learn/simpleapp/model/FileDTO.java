package com.learn.simpleapp.model;

public class FileDTO {
	private String name;
	private Double size;
	private String location;
	
	public FileDTO() {}

	public FileDTO(String name, Double size, String location) {
		this.name = name;
		this.size = size;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
