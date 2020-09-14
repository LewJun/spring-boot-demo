package com.example.lewjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot 启动类
 */
@SpringBootApplication
public class App2 {
    public static void main(final String[] args) {
        System.setProperty("server.port", "0");
        SpringApplication.run(App2.class, args);
    }
}
