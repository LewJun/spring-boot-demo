package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring boot 测试类
 */
@SpringBootTest
@Slf4j
public class AppTest {
    @Test
    public void testLoadContext() {
        log.trace("trace: {}", "trace");
        log.debug("debug: {}", "debug");
        log.info("info: {}", "info");
        log.warn("warn: {}", "warn");
        log.error("error: {}", "error");

        try {
            final int i = 1 / 0;
        } catch (final Exception ex) {
            log.error("出现异常", ex);
        }
    }
}
