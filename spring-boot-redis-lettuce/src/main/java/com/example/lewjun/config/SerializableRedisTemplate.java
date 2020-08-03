package com.example.lewjun.config;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

public class SerializableRedisTemplate extends RedisTemplate<String, Serializable> {
    public SerializableRedisTemplate() {
        this.setKeySerializer(new StringRedisSerializer());
        this.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }

    public SerializableRedisTemplate(final RedisConnectionFactory connectionFactory) {
        this();
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }
}
