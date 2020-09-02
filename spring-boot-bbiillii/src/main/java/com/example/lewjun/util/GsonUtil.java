package com.example.lewjun.util;

import com.google.gson.*;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class GsonUtil {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {// Date转long
                @Override
                public JsonElement serialize(final Date date, final Type type, final JsonSerializationContext jsonSerializationContext) {
                    return new JsonPrimitive(date.getTime());
                }
            }).setDateFormat(DateFormat.LONG)
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {// long转Date
                public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            }).setDateFormat(DateFormat.LONG)

            .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {// LocalDate转long
                @Override
                public JsonElement serialize(final LocalDate date, final Type type, final JsonSerializationContext jsonSerializationContext) {
                    return new JsonPrimitive(DateUtils.asLong(date));
                }
            }).setDateFormat(DateFormat.LONG)
            .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {// long转LocalDate
                public LocalDate deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
                    return DateUtils.asLocalDate(json.getAsJsonPrimitive().getAsLong());
                }
            }).setDateFormat(DateFormat.LONG)

            .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {// LocalDateTime转long
                @Override
                public JsonElement serialize(final LocalDateTime date, final Type type, final JsonSerializationContext jsonSerializationContext) {
                    return new JsonPrimitive(DateUtils.asLong(date));
                }
            }).setDateFormat(DateFormat.LONG)
            .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {// long转LocalDate
                public LocalDateTime deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
                    return DateUtils.asLocalDateTime(json.getAsJsonPrimitive().getAsLong());
                }
            }).setDateFormat(DateFormat.LONG)
            .disableHtmlEscaping()
            .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(final FieldAttributes fieldAttributes) {
                    final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                    return expose != null && !expose.deserialize();
                }

                @Override
                public boolean shouldSkipClass(final Class<?> aClass) {
                    return false;
                }
            })
            .create();

    public static String objToJsonString(final Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T jsonStringToObj(final String json, final Type type) {
        return GSON.fromJson(json, type);
    }
}
