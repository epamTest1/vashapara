package com.couple.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.TreeMap;

public class SendNotification extends BaseVKController {

    private final String message = "Ваш друг предлагает Вам пройти с ним тест.";

    public void send(String userId) throws IOException {
        // temporary check
        if (null == apiURL) apiURL = "http:\\\\vk.com\\api.php";

        TreeMap<String, String> params = new TreeMap<String, String>();

        params.put("api_id", api_id);
        params.put("format", "json");
        params.put("message", message);
        params.put("method", "secure.sendNotification");
        params.put("random", String.valueOf((new Random()).nextLong()));
        params.put("timestamp", String.valueOf(Calendar.getInstance().getTimeInMillis()));
        params.put("uids", userId);
        params.put("v", api_ver);

        request(params);
        /*
        ObjectMapper mapper = new ObjectMapper();

        ArrayList response = ((ArrayList) mapper.readValue(requestToVK(params), Map.class).get("response"));*/

    }
}