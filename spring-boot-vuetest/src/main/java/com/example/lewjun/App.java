package com.example.lewjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring boot 启动类
 */
@SpringBootApplication
@RestController
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }
}
