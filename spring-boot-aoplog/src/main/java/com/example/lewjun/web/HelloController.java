package com.example.lewjun.web;

import com.example.lewjun.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Log("/log is logged")
    @GetMapping("/log")
    public String log(@RequestParam(name = "name", defaultValue = "World") final String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") final String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/")
    public String index() {
        return "/";
    }
}
