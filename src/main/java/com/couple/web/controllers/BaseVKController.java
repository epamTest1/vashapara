package com.couple.web.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class BaseVKController {

    protected final String api_secret = "ogoSgljHJqUGk9fnbzLa";
    protected String apiURL;

    protected String getSIG(Map<String, String> params) {
        String data = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            // System.out.println(entry.getKey() + "=" + entry.getValue());
            data += entry.getKey() + "=" + entry.getValue();
        }
        data += api_secret;
        return DigestUtils.md5Hex(data);
    }

    protected String getParamsAsString(Map<String, String> params) {
        String data = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            data += entry.getKey() + "=" + entry.getValue() + "&";
        }
        return data;
    }

    protected String requestToVK(Map<String, String> params) throws IOException, ClientProtocolException, UnsupportedEncodingException {
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

}
