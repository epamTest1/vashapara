package com.couple.services.external;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.couple.services.external.User.Sex;


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
			Map<String, String> params = buildRequestParams("users.get");
			params.put("fields", VKUserFields.getList());
			params.put("uids", userId);
			
			List<Map<String, Object>> response = performJsonRequest(params);
			if (!response.isEmpty()) {
				return VkApiService.map((Map<String, Object>) response.get(0));
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new SocialApiException(e);
		}
	}
	
	@Override
	public List<Map<String, Object>> getFriends(String userId, Sex sex) throws SocialApiException {
		Map<String, String> params = buildRequestParams("friends.get");
		params.put("uid", userId);
		params.put("fields", VKUserFields.getList());
		params.put("count", Integer.toString(MAX_FRIENDS_TO_RECEIVE_FROM_API));
		
		List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		
		int offset = 0;
		try {
			do {
				params.put("offset", Integer.toString(offset));
				
				response = performJsonRequest(params);
				
				for (Map<String, Object> friendMap : response) {
					User friend = VkApiService.map(friendMap);
					
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
	
	private Map<String, String> buildRequestParams(String method) {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("api_id", API_ID);
		params.put("method", method);
		params.put("v", API_VER);
		params.put("format", API_FORMAT);
		
		return params;
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> performJsonRequest(Map<String, String> params) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> friendsList = mapper.readValue(performRequest(params), Map.class);
		return (List<Map<String, Object>>) friendsList.get("response");
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

	private static User map(Map<String, Object> info) throws NullPointerException {
		User user = new User(String.valueOf(info.get(VKUserFields.UID.toString())), String.valueOf(info.get(VKUserFields.FIRST_NAME.toString())));
		user.setSex(User.Sex.forCode((Integer) info.get(VKUserFields.SEX.toString())));
		
		String photoUrl = null;
		if (info.containsKey(VKUserFields.PHOTO_BIG.toString()) && !info.get(VKUserFields.PHOTO_BIG.toString()).toString().isEmpty()) {
			photoUrl = info.get(VKUserFields.PHOTO_BIG.toString()).toString();
		} else if (info.containsKey(VKUserFields.PHOTO_MEDIUM.toString()) && !info.get(VKUserFields.PHOTO_MEDIUM.toString()).toString().isEmpty()) {
			photoUrl = info.get(VKUserFields.PHOTO_MEDIUM.toString()).toString();
		} else if (info.containsKey(VKUserFields.PHOTO.toString()) && !info.get(VKUserFields.PHOTO.toString()).toString().isEmpty()) {
			photoUrl = info.get(VKUserFields.PHOTO.toString()).toString();
		}
		user.setImageUrl(photoUrl);
		
		return user;
	}
}
