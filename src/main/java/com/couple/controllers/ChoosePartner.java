package com.couple.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChoosePartner {
	private final String api_secret = "ogoSgljHJqUGk9fnbzLa";
	private final int MAX_FRIENDS_TO_RECEIVE_FROM_API = 20;
	private final int SEX_MALE = 2;
	private final int SEX_FEMALE = 1;
	private final int SEX_NOT_SET = 0;
	private String apiURL;

	private String getSIG(Map<String, String> params) {
		String data = "";
		for (Map.Entry<String, String> entry : params.entrySet()) {
			// System.out.println(entry.getKey() + "=" + entry.getValue());
			data += entry.getKey() + "=" + entry.getValue();
		}
		data += api_secret;
		return DigestUtils.md5Hex(data);
	}

	private String getParamsAsString(Map<String, String> params) {
		String data = "";
		for (Map.Entry<String, String> entry : params.entrySet()) {
			data += entry.getKey() + "=" + entry.getValue() + "&";
		}
		return data;
	}

	private Map getUserInfo(String uid) throws ClientProtocolException, IOException {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("api_id", "2857279");
		params.put("method", "users.get");
		params.put("format", "json");
		params.put("fields", "uid,first_name,last_name,photo,sex");
		params.put("v", "3.0");
		params.put("uids", uid);
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		ArrayList response = ((ArrayList) mapper.readValue(requestToVK(params), Map.class).get("response"));
		if (response.isEmpty())
			return new HashMap();
		else
			return (Map) response.get(0);
	}

	// sex: 1 - женский, 2 - мужской, 0 - без указания пола.
	private List<Map> friends_get(String viewerID, int step, Integer sex) throws ClientProtocolException, IOException {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		List<Map> result = new LinkedList();

		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("api_id", "2857279");
		params.put("method", "friends.get");
		params.put("v", "3.0");
		params.put("format", "json");
		params.put("uid", viewerID);
		params.put("fields", "uid,first_name,last_name,photo,sex");
		params.put("count", Integer.toString(step));
		// params.put("name_case", "nom");
		// params.put("offset", "0");
		// params.put("timestamp", Integer.toString((int) (System.currentTimeMillis() / 1000L)));
		// params.put("random", "40275037");

		int offset = 0;
		Map<String, Object> friendsList = new HashMap();
		do {
			params.put("offset", Integer.toString(offset));
			String friendsListAsJSON = requestToVK(params);
			friendsList = mapper.readValue(friendsListAsJSON, Map.class);
			for (Map friend : (ArrayList<LinkedHashMap>) friendsList.get("response")) {
				Object friendSex = friend.get("sex");
				if (friendSex.equals(sex) || friendSex.equals(SEX_NOT_SET) || sex.equals(SEX_NOT_SET)) {
					result.add(friend);
				}
			}
			offset += step;
		} while (!((ArrayList<Map>) friendsList.get("response")).isEmpty());
		return result;
	}

	private String friends_getAppUsers(String viewerID) throws ClientProtocolException, IOException {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("api_id", "2857279");
		params.put("method", "friends.getOnline");
		params.put("format", "json");
		params.put("uid", viewerID);
		String result = "friends_getAppUsers: " + requestToVK(params);
		return result;
	}

	private String requestToVK(Map<String, String> params) throws IOException, ClientProtocolException, UnsupportedEncodingException {
		String requestURL = apiURL + "?";
		requestURL += getParamsAsString(params);
		requestURL += "sig=" + getSIG(params);
		String result = "";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(requestURL);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(instream, "utf8"));
			while (br.ready()) {
				String str = br.readLine();
				// System.out.println(str);
				result += str;
			}
		}
		return result;
	}

	@RequestMapping(value = "/", method = GET)
	public ModelAndView choosePartner(HttpServletRequest request) throws ClientProtocolException, IOException {
		ModelAndView res = new ModelAndView();
		apiURL = request.getParameter("api_url");
		String viewerID = request.getParameter("viewer_id");

		Map currentUser = getUserInfo(viewerID);
		Integer sex = (Integer) currentUser.get("sex");
		int sexParam = SEX_NOT_SET;
		if (sex.equals(SEX_MALE))
			sexParam = SEX_FEMALE;
		else if (sex.equals(SEX_FEMALE))
			sexParam = SEX_MALE;

		res.setViewName("choose-partner");
		List<Map> friendsList = friends_get(viewerID, MAX_FRIENDS_TO_RECEIVE_FROM_API, sexParam);
		res.addObject("friendsList", friendsList);
		res.addObject("myId", viewerID);
		
		return res;
	}
}

