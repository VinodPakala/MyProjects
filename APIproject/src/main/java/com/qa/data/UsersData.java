package com.qa.data;

// POJO class
public class UsersData {
	
	String name;
	String job;
	String id;
	String CreatedAt; 
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(String createdAt) {
		CreatedAt = createdAt;
	}

	public UsersData(){
		
	}
	
	public UsersData(String name, String job) {
		this.name = name;
		this.job = job;
	}

	// getters and setters for name and job variables
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
