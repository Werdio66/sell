package com.lx.sell.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author Werdio丶
 * @since 2020-05-24 08:46:36
 */
public class JsonUtil {

    private static final Gson gson = new Gson();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static <T>T formJson(String json, Type typeOfT){
        return gson.fromJson(json, typeOfT);
    }

}
