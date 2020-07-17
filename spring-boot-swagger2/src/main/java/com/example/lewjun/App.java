package com.example.lewjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * spring boot 启动类
 */
@EnableOpenApi
@SpringBootApplication
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }
}
