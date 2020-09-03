package com.example.lewjun

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * spring boot 启动类
 */
@SpringBootApplication
@RestController
class App {
    static void main(String[] args) {
        SpringApplication.run(App.class, args)
    }

    @GetMapping("/hello")
    String hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name)
    }

    @GetMapping("/")
    String index() {
        return "/"
    }
}
