package com.example.lewjun;

import com.example.lewjun.config.SerializableRedisTemplate;
import com.example.lewjun.domain.Ab01;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class AppTest {
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Autowired
    private SerializableRedisTemplate serializableRedisTemplateCustom;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testLoadContext() {
        final ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("uname", "zs");
        final String uname = stringStringValueOperations.get("uname");
        log.info("【uname: {}】", uname);

        redisTemplate.opsForValue().set("myname", "ls");

        serializableRedisTemplate.opsForValue().set("ab01",
                Ab01.builder()
                        .aab001(10)
                        .aab002("aab002")
                        .aab003("aab003")
                        .build()
        );
        final Ab01 ab01 = (Ab01) serializableRedisTemplate.opsForValue().get("ab01");
        log.info("【ab01: {}】", ab01);

        serializableRedisTemplateCustom.opsForValue().set("ab01x",
                Ab01.builder().aab001(20).aab002("aab002x").aab003("aab003x").build()
        );
        final Ab01 ab01x = (Ab01) serializableRedisTemplateCustom.opsForValue().get("ab01x");
        log.info("【ab01x:{}】", ab01x);
    }
}
