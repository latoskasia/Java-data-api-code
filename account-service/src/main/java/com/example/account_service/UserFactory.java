package com.example.account_service;

import org.json.JSONObject;

public class UserFactory {

    public static String getUserAsJSONString(User user) {
        JSONObject jo = new JSONObject();
        jo.put("name", user.getName());
        jo.put("email", user.getEmail());
        jo.put("password", user.getPassword());
        jo.put("id", user.getId());
        return jo.toString();
    }
}