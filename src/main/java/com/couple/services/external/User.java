package com.couple.services.external;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;


public class User {
	private String id;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("photo_big")
	private String bigPhotoUrl;
	
	@JsonProperty("photo_medium")
	private String mediumPhotoUrl;
	
	@JsonProperty("photo")
	private String smallPhotoUrl;
	
	@JsonProperty("sex")
	private Sex sex = Sex.NOT_SET;
	
	@JsonDeserialize(using = SexDeserializer.class)
	public enum Sex {NOT_SET, FEMALE, MALE}
	
	public User(@JsonProperty("uid") String id) {
		if (id == null) {
			throw new NullPointerException("Either ID or name is null");
		}
		
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getBigPhotoUrl() {
		return bigPhotoUrl;
	}
	
	public void setBigPhotoUrl(String bigPhotoUrl) {
		this.bigPhotoUrl = bigPhotoUrl;
	}
	
	public String getMediumPhotoUrl() {
		return mediumPhotoUrl;
	}
	
	public void setMediumPhotoUrl(String mediumPhotoUrl) {
		this.mediumPhotoUrl = mediumPhotoUrl;
	}
	
	public String getSmallPhotoUrl() {
		return smallPhotoUrl;
	}
	
	public void setSmallPhotoUrl(String smallPhotoUrl) {
		this.smallPhotoUrl = smallPhotoUrl;
	}
	
	public Sex getSex() {
		return sex;
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}
}
