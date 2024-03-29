package com.couple.services.external;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import org.codehaus.jackson.type.JavaType;

import com.couple.services.external.User.Sex;


class VkApiService implements SocialApiService {
	private final static String API_SECRET = "ogoSgljHJqUGk9fnbzLa";
	private final static String API_ID = "2857279";
	private final static String API_VER = "3.0";
	private final static String API_FORMAT = "json";
	private final static String CODE_PAGE = "UTF-8";
	
	private final int MAX_FRIENDS_TO_RECEIVE_FROM_API = 20;
	
	private HttpClient httpclient = new DefaultHttpClient();
	private ObjectMapper mapper = new ObjectMapper();
	private final String apiUrl;
	
	public VkApiService(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	void setHttpClient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
	
	@Override
	public User getUser(String userId) throws SocialApiException {
		try {
			Map<String, String> params = buildRequestParams("users.get");
			params.put("fields", VKUserFields.getList());
			params.put("uids", userId);
			
			List<User> response = performJsonRequest(params, User.class);
			if (!response.isEmpty()) {
				return response.get(0);
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new SocialApiException(e);
		}
	}
	
	@Override
	public List<User> getFriends(String userId, Sex sex) {
		Map<String, String> params = buildRequestParams("friends.get");
		params.put("uid", userId);
		params.put("fields", VKUserFields.getList());
		params.put("count", Integer.toString(MAX_FRIENDS_TO_RECEIVE_FROM_API));
		
		List<User> result = new LinkedList<User>();
		
		List<User> usersResponse = null;
		int offset = 0;
		try {
			do {
				params.put("offset", Integer.toString(offset));
				
				usersResponse = performJsonRequest(params, User.class);
				for(User friend: usersResponse) {
					if (friend.getSex() == sex || friend.getSex() == Sex.NOT_SET || sex == Sex.NOT_SET) {
						result.add(friend);
					}
				}
				
				offset += MAX_FRIENDS_TO_RECEIVE_FROM_API;
			} while (! usersResponse.isEmpty());
		} catch (IOException e) {
			throw new SocialApiException(e);
		}
		
		return result;
	}
	
	private Map<String, String> buildRequestParams(String method) {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("api_id", API_ID);
		params.put("method", method);
		params.put("v", API_VER);
		params.put("format", API_FORMAT);
		
		return params;
	}
	
	private <T> List<T> performJsonRequest(Map<String, String> params, Class<T> type) throws IOException {
		String jsonString = performRequest(params);
		
		JavaType responseType = mapper.getTypeFactory().constructParametricType(ResponseWrapper.class, type);
		ResponseWrapper<T> responseList = mapper.readValue(jsonString, responseType);
		
		List<T> response = responseList.getResponse();
		return response != null ? response : Collections.<T>emptyList();
	}
	
	private String performRequest(Map<String, String> params) throws IOException {
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
