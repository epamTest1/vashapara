package com.couple.services.external;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.couple.web.dto.User;
import com.couple.web.dto.VKUserFields;
import com.couple.web.dto.User.Sex;


class VkApiService implements SocialApiService {
	private final static String API_SECRET = "ogoSgljHJqUGk9fnbzLa";
	private final static String API_ID = "2857279";
	private final static String API_VER = "3.0";
	private final static String API_FORMAT = "json";
	private final static String CODE_PAGE = "UTF-8";
	
	private final int MAX_FRIENDS_TO_RECEIVE_FROM_API = 20;
	
	private final HttpClient httpclient = new DefaultHttpClient();
	private final String apiUrl;
	
	public VkApiService(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	@Override
	public User getUser(String userId) throws SocialApiException {
		try {
			return User.map(getUserInfo(userId));
		} catch (IOException e) {
			throw new SocialApiException(e);
		}
	}
	
	@Override
	public List<Map<String, Object>> getFriends(String userId, Sex sex) throws SocialApiException {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("api_id", API_ID);
		params.put("method", "friends.get");
		params.put("v", API_VER);
		params.put("format", API_FORMAT);
		params.put("uid", userId);
		params.put("fields", VKUserFields.getList());
		params.put("count", Integer.toString(MAX_FRIENDS_TO_RECEIVE_FROM_API));
		
		List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		
		int offset = 0;
		try {
			do {
				params.put("offset", Integer.toString(offset));
				
				response = performRequest(params);
				
				for (Map<String, Object> friendMap : response) {
					User friend = User.map(friendMap);
					
					if (friend.getSex() == sex || friend.getSex() == Sex.NOT_SET || sex == Sex.NOT_SET) {
						result.add(friendMap);
					}
				}
				
				offset += MAX_FRIENDS_TO_RECEIVE_FROM_API;
			} while (! response.isEmpty());
		} catch (IOException e) {
			throw new SocialApiException(e);
		}
		
		return result;
	}
	
	private Map<String, Object> getUserInfo(String uid) throws IOException {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("api_id", API_ID);
		params.put("method", "users.get");
		params.put("format", API_FORMAT);
		params.put("fields", VKUserFields.getList());
		params.put("v", API_VER);
		params.put("uids", uid);
		
		List<Map<String, Object>> response = performRequest(params);
		if (response.isEmpty()) {
			return Collections.emptyMap();
		} else {
			return (Map<String, Object>) response.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> performRequest(Map<String, String> params) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> friendsList = mapper.readValue(request(params), Map.class);
		return (List<Map<String, Object>>) friendsList.get("response");
	}
	
	private String request(Map<String, String> params) throws IOException {
		String requestURL = apiUrl + "?" + prepareQueryString(params);
		
		HttpResponse response = httpclient.execute(new HttpGet(requestURL));
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			return EntityUtils.toString(entity, CODE_PAGE);
		} else {
			return "";
		}
	}
	
	
	private String prepareQueryString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder data = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			data.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), CODE_PAGE)).append('&');
		}
		data.append("sig=").append(getSignature(params));
		
		return data.toString();
	}
	
	private String getSignature(Map<String, String> params) {
		StringBuilder data = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			data.append(entry.getKey()).append('=').append(entry.getValue());
		}
		data.append(API_SECRET);
		
		return DigestUtils.md5Hex(data.toString());
	}
}