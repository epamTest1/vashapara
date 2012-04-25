package com.couple.services.external;

import java.util.List;

import com.couple.services.external.User.Sex;

public interface SocialApiService {
	public User getUser(String userId) throws SocialApiException;
	public List<User> getFriends(String userId, Sex sex);
}
