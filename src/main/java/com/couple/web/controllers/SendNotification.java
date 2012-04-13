package com.couple.web.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class SendNotification extends BaseVKController {

	private final static String DEFAULT_MESSAGE = "Ваш друг предлагает Вам пройти с ним тест.";

	public void send(String userId) throws IOException {
		// temporary check
		if (null == apiURL) apiURL = "http:\\\\vk.com\\api.php";

		Map<String, String> params = new TreeMap<String, String>();

		params.put("api_id", API_ID);
		params.put("format", API_FORMAT);
		params.put("message", DEFAULT_MESSAGE);
		params.put("method", "secure.sendNotification");
		params.put("random", String.valueOf((new Random()).nextLong()));
		params.put("timestamp", String.valueOf(Calendar.getInstance().getTimeInMillis()));
		params.put("uids", userId);
		params.put("v", API_VER);

		request(params);
	}
}
