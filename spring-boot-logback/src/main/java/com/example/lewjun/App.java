package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot 启动类
 */
@SpringBootApplication
@Slf4j
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);

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
