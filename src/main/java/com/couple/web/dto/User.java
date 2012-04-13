package com.couple.web.dto;

import java.util.Map;

public class User {

	private static final String NO_NAME = "anonimous";
	private static final String NO_PHOTO = "http://placehold.it/200x260";

	private String id;
	private String name;
	private String imageUrl;

	public User(String id, String name, String imageUrl) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
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

	public static User map(Map<String, String> info) throws NullPointerException {

		String name = NO_NAME;
		String photoURL = NO_PHOTO;

		if (!info.containsKey(VKUserFields.uid.toString())) {
			throw new NullPointerException("User uid is empty.");
		}

		if (info.containsKey(VKUserFields.first_name.toString()) && !info.get(VKUserFields.first_name.toString()).toString().isEmpty()) {
			name = info.get(VKUserFields.first_name.toString()).toString();
		}

		if (info.containsKey(VKUserFields.photo_big.toString()) && !info.get(VKUserFields.photo_big.toString()).toString().isEmpty()) {
			photoURL = info.get(VKUserFields.photo_big.toString()).toString();

		} else if (info.containsKey(VKUserFields.photo_medium.toString()) && !info.get(VKUserFields.photo_medium.toString()).toString().isEmpty()) {
			photoURL = info.get(VKUserFields.photo_medium.toString()).toString();

		} else if (info.containsKey(VKUserFields.photo.toString()) && !info.get(VKUserFields.photo.toString()).toString().isEmpty()) {
			photoURL = info.get(VKUserFields.photo.toString()).toString();
		}

		return new User(String.valueOf(info.get(VKUserFields.uid.toString())), name, photoURL);
	}
}
