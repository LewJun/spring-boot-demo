package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class AppTest {
    @Value("${env.name}")
    private String envName;

    @Value("${server.port}")
    private int serverPort;

    @Test
    public void testLoadContext() {
        log.info("envName: {}", envName);
        log.info("serverPort: {}", serverPort);
    }
}
