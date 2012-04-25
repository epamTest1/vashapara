package com.couple.services.external;


public class User {
	private String id;
	private String name;
	private String imageUrl;
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
	
	public User(String id, String name) {
		if (id == null || name == null) {
			throw new NullPointerException("Either ID or name is null");
		}
		
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public Sex getSex() {
		return sex;
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}
}
