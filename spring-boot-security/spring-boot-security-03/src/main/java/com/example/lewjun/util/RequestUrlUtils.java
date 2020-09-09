package com.example.lewjun.util;

public class RequestUrlUtils {
    public static String getRequestUrlWithoutQueryParams(final String requestUrl) {
        final int index = requestUrl.indexOf("?");
        return requestUrl.substring(0, index == -1 ? requestUrl.length() : index);
    }
}
