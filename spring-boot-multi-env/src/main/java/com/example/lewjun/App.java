package com.example.lewjun;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring boot 启动类
 */
@SpringBootApplication
@RestController
@Slf4j
public class App {
    @Value("${env.name}")
    private String envName;

    @Value("${server.port}")
    private int serverPort;

    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") final String name) {
        log.info("envName: {}", envName);
        log.info("serverPort: {}", serverPort);
        return String.format("Hello %s!", name);
    }

    @GetMapping("/")
    public String index() {
        log.info("envName: {}", envName);
        log.info("serverPort: {}", serverPort);
        return "/";
    }
}
