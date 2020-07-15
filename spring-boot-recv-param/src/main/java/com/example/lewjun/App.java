package com.example.lewjun;

import com.example.lewjun.model.Ab01;
import com.example.lewjun.model.Ac01;
import com.example.lewjun.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * spring boot 启动类
 */
@Slf4j
@SpringBootApplication
@RestController
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/request_param")
    public String request_param(@RequestParam(name = "name", defaultValue = "World") final String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/request_param/post")
    public String request_param_post(@RequestParam(name = "name", defaultValue = "World") final String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/path_variable/{hello}/{world:[0-9]{5}}")
    public String path_variable(@PathVariable("hello") final String hello, @PathVariable("world") final String world) {
        return String.format("%s %s!", hello, world);
    }

    @RequestMapping("/auto_match")
    public String auto_match(final String hello, final String world) {
        return String.format("%s %s!", hello, world);
    }

    @RequestMapping("/pass_ab01")
    public String pass_ab01(final Ab01 ab01) {
        log.info("ab01: {}", ab01);
        return ab01.toString();
    }

    @RequestMapping("/request_body")
    public String request_body(@RequestBody final Ab01 ab01) {
        log.info("ab01: {}", ab01);
        return GsonUtil.objToJsonString(ab01);
    }

    @RequestMapping("/request_body_ac01")
    public String request_body_ac01(@RequestBody final Ac01 ac01) {
        log.info("ac01: {}", ac01);
        return GsonUtil.objToJsonString(ac01);
    }

    @GetMapping("/")
    public String index() {
        return "/";
    }
}
