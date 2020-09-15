package com.example.lewjun.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 驼峰转下划线格式
 */
public class CamelUnderlineUtil {

    private static final char UNDERLINE = '_';

    public static String camelToUnderline(final String line) {
        if (line == null) {
            return "";
        }
        final String pattern = "\\B[A-Z]";
        final Pattern p = Pattern.compile(pattern);
        final Matcher matcher = p.matcher(line);

        final StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, UNDERLINE + matcher.group().toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
