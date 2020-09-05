package com.example.lewjun.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    public static String object2String(final Object obj) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writer().writeValueAsString(obj);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
