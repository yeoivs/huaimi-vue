package com.ieng.huaimi.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class JSONUtil {

    public static String toJsonString(Object o){
        return JSON.toJSONString(o);
    }

    public static <T> T gsonToObject(String json, Class<T> clazz){
        return new Gson().fromJson(json, clazz);
    }

    public static <T> T jsonToObject(String json, Class<T> clazz){
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
