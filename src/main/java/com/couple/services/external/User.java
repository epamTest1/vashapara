package com.couple.services.external;


public class User {
	private String id;
	private String firstName;
	private String lastName;
	private String bigPhotoUrl;
	private String mediumPhotoUrl;
	private String smallPhotoUrl;
	private Sex sex = Sex.NOT_SET;

	public enum Sex {
		NOT_SET(0),
		FEMALE(1),
		MALE(2);
		
		private final int code;
		
		private Sex(int code) {
			this.code = code;
		}
		
		static Sex forCode(int code) {
			for(Sex sex: values()) {
				if (sex.code == code) {
					return sex;
				}
			}
			
			throw new IllegalArgumentException("Unknown sex code");
		}
	}
	
	public User(String id) {
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
