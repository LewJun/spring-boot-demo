package com.example.lewjun.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * long转Date
 */
public class LongToDateJsonDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return new Date(jsonParser.getLongValue());
    }
}
