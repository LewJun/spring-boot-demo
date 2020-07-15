package com.example.lewjun;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot 启动类
 */
@EnableAdminServer
@SpringBootApplication
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }
}
