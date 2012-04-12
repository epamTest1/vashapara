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

	public static User map(Map<String, String> info) {

		String uid = null;
		String name = null;
		String photoURL = null;

		if (info.containsKey("uid")) {
			uid = String.valueOf(info.get("uid"));
		}
		if (uid == null || uid.isEmpty()) {
			return null;
		}

		if (info.containsKey("first_name")) {
			name = info.get("first_name").toString();
		} else {
			name  = info.get("last_name").toString();
		}

		if (name == null || name.isEmpty()) {
			name = NO_NAME;
		}

		if (info.containsKey("photo_big")) {
			photoURL = info.get("photo_big").toString();
		} else if (info.containsKey("photo_medium")) {
			photoURL = info.get("photo_medium").toString();
		} else if (info.containsKey("photo")) {
			photoURL = info.get("photo").toString();
		}

		if (photoURL == null || photoURL.isEmpty()) {
			photoURL = NO_PHOTO;
		}

		return new User(uid, name, photoURL);
	}
}
