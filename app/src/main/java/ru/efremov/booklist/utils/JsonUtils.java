package ru.efremov.booklist.utils;

import com.google.gson.Gson;

/**
 * JSON工具类
 *
 */
public class JsonUtils {
    private static Gson      gson  = new Gson();

    public static Gson getGson() {
        return gson;
    }

}
