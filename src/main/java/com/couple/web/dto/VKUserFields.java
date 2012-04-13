package com.couple.web.dto;

import org.springframework.util.StringUtils;

public enum VKUserFields {

	uid(),
	first_name(),
	last_name(),
	photo(),
	photo_medium(),
	photo_big(),
	sex();

	public static String getList() {
		return StringUtils.arrayToCommaDelimitedString(VKUserFields.values());
	}
}
