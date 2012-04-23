package com.couple.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.couple.web.dto.User;
import com.couple.web.dto.VKUserFields;

@Controller
@RequestMapping("/choose-partner")
public class ChoosePartnerController extends BaseVKController {

	private final int MAX_FRIENDS_TO_RECEIVE_FROM_API = 20;
	private final int SEX_MALE = 2;
	private final int SEX_FEMALE = 1;
	private final int SEX_NOT_SET = 0;

	private Map getUserInfo(String uid) throws ClientProtocolException, IOException {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("api_id", API_ID);
		params.put("method", "users.get");
		params.put("format", API_FORMAT);
		params.put("fields", VKUserFields.getList());
		params.put("v", API_VER);
		params.put("uids", uid);
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		ArrayList response = ((ArrayList) mapper.readValue(request(params), Map.class).get("response"));
		if (response.isEmpty())
			return new HashMap();
		else
			return (Map) response.get(0);
	}

	// sex: 1 - женский, 2 - мужской, 0 - без указания пола.
	private List<Map> getFriends(String viewerID, int step, Integer sex) throws ClientProtocolException, IOException {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		List<Map> result = new LinkedList();

		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("api_id", API_ID);
		params.put("method", "friends.get");
		params.put("v", API_VER);
		params.put("format", API_FORMAT);
		params.put("uid", viewerID);
		params.put("fields", VKUserFields.getList());
		params.put("count", Integer.toString(step));
		// params.put("name_case", "nom");
		// params.put("offset", "0");
		// params.put("timestamp", Integer.toString((int)
		// (System.currentTimeMillis() / 1000L)));
		// params.put("random", "40275037");

		int offset = 0;
		Map<String, Object> friendsList = new HashMap();
		do {
			params.put("offset", Integer.toString(offset));
			String friendsListAsJSON = request(params);
			friendsList = mapper.readValue(friendsListAsJSON, Map.class);
			for (Map friend : (ArrayList<LinkedHashMap>) friendsList.get("response")) {
				Object friendSex = friend.get(VKUserFields.sex.toString());
				if (friendSex.equals(sex) || friendSex.equals(SEX_NOT_SET) || sex.equals(SEX_NOT_SET)) {
					result.add(friend);
				}
			}
			offset += step;
		} while (!((ArrayList<Map>) friendsList.get("response")).isEmpty());
		return result;
	}

	/*
	private String friends_getAppUsers(String viewerID) throws ClientProtocolException, IOException {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("api_id", "2857279");
		params.put("method", "friends.getOnline");
		params.put("format", "json");
		params.put("uid", viewerID);
		String result = "friends_getAppUsers: " + request(params);
		return result;
	}
	*/
	
	public User getUser(String userId) throws ClientProtocolException, IOException {
		return User.map(getUserInfo(userId));
	}

	@RequestMapping(value = "/{myId}", method = GET)
	public ModelAndView choosePartner(HttpSession session, @PathVariable("myId") String myId) throws IOException {
		apiURL = (String) session.getAttribute("apiUrl");
		
		Map currentUser = getUserInfo(myId);
		Integer sex = (Integer) currentUser.get(VKUserFields.sex.toString());
		int sexParam = SEX_NOT_SET;
		if (sex.equals(SEX_MALE))
			sexParam = SEX_FEMALE;
		else if (sex.equals(SEX_FEMALE))
			sexParam = SEX_MALE;

		List<Map> friendsList = getFriends(myId, MAX_FRIENDS_TO_RECEIVE_FROM_API, sexParam);
		
		ModelAndView res = new ModelAndView("choose-partner");
		res.addObject("myId", myId);
		res.addObject("friendsList", friendsList);

		return res;
	}
}
