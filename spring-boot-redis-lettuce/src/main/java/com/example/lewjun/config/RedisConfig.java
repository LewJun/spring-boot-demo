package com.example.lewjun.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Serializable> serializableRedisTemplate(final LettuceConnectionFactory connectionFactory) {
        final RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        // key 用String来表示
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // value 使用jackson来转换为json
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
