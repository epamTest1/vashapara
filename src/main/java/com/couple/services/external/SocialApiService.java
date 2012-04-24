package com.couple.services.external;

import java.util.List;
import java.util.Map;

import com.couple.web.dto.User;
import com.couple.web.dto.User.Sex;

public interface SocialApiService {
	public User getUser(String userId) throws SocialApiException;
	public List<Map<String, Object>> getFriends(String userId, Sex sex);
}
