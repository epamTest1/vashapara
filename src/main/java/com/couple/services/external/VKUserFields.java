package com.couple.services.external;

import org.springframework.util.StringUtils;

enum VKUserFields {
	UID,
	FIRST_NAME,
	LAST_NAME,
	PHOTO,
	PHOTO_MEDIUM,
	PHOTO_BIG,
	SEX;
	
	public static String getList() {
		return StringUtils.arrayToCommaDelimitedString(VKUserFields.values());
	}
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
