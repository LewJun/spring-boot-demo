package com.example.lewjun.util;

public class RequestUrlUtils {
    public static String getRequestUrlWithoutQueryParams(final String requestUrl) {
        final int index = requestUrl.indexOf("?");
        String ret = requestUrl;
        if (index != -1) {
            ret = requestUrl.substring(0, index);
        }

        if (!"/".equals(ret) && ret.endsWith("/")) {
            ret = ret.substring(0, ret.lastIndexOf("/"));
        }

        return ret;
    }
}
