package com.example.lewjun.config;

import com.example.lewjun.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 日期和long之间的相互转换
 */
@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(Date.class, new DateToLongJsonSerializer());
        javaTimeModule.addDeserializer(Date.class, new LongToDateJsonDeserializer());

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateToLongJsonSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LongToLocalDateJsonDeserializer());

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeToLongJsonSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LongToLocalDateTimeJsonDeserializer());

        objectMapper.registerModule(javaTimeModule);

        return objectMapper;
    }
}
