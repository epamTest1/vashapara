package com.couple.web.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class BaseVKController {

	protected final static String API_SECRET = "ogoSgljHJqUGk9fnbzLa";
	protected final static String API_ID = "2857279";
	protected final static String API_VER = "3.0";

	protected final static String API_FORMAT = "json";

	protected final static String CODE_PAGE = "UTF-8";

	protected String apiURL;

	protected String getSIG(Map<String, String> params) {
		StringBuilder data = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			data.append(entry.getKey()).append('=').append(entry.getValue());
		}
		data.append(API_SECRET);
		return DigestUtils.md5Hex(data.toString());
	}

	protected String getParamsAsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder data = new StringBuilder(32);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if ("message".equals(entry.getKey())) {
				data.append(entry.getKey()).append('=');
				data.append(URLEncoder.encode(entry.getValue(), CODE_PAGE)).append('&');
			} else {
				data.append(entry.getKey()).append('=');
				data.append(entry.getValue()).append('&');
			}

		}
		return data.toString();
	}

	protected String request(Map<String, String> params) throws IOException, ClientProtocolException, UnsupportedEncodingException {
		String requestURL = apiURL + "?";
		requestURL += getParamsAsString(params);
		requestURL += "sig=" + getSIG(params);

		StringBuilder result = new StringBuilder(32);

		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(requestURL);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(instream, CODE_PAGE));
			String str = null;
			while (br.ready()) {
				str = br.readLine();
				result.append(str);
			}
		}
		return result.toString();
	}

}
