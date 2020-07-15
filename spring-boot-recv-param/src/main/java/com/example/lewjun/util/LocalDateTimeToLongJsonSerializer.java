package com.example.lewjun.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * LocalDateTime转long
 */
public class LocalDateTimeToLongJsonSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(final LocalDateTime localDateTime, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(DateUtils.asLong(localDateTime));
    }
}
