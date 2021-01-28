package com.example.lewjun;

import com.example.lewjun.model.Ab01;
import com.example.lewjun.model.Ac01;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public Ab01 pass_ab01(final Ab01 ab01) {
        log.info("ab01: {}", ab01);
        return ab01;
    }

    @RequestMapping("/request_body")
    public Ab01 request_body(@RequestBody final Ab01 ab01) {
        log.info("ab01: {}", ab01);
        return ab01;
    }

    @RequestMapping("/request_body_ac01")
    public Ac01 request_body_ac01(@RequestBody final Ac01 ac01) {
        log.info("ac01: {}", ac01);
        return ac01;
    }

    /**
     * 根据map查询
     *
     * @param map 参数必须添加@RequestParam注解
     * @return
     */
    @GetMapping("/findByMap")
    public Map<String, Object> findByMap(@RequestParam final Map<String, Object> map) {
        log.info("【map:{}】", map);
        return map;
    }

    /**
     * findByMap除了可以用map接收外，还可以使用对象接收。但是对象就不要使用@RequestParam进行注释了
     */
    @GetMapping("/findByObj")
    public Ab01 findByObj(final Ab01 ab01) {
        log.info("【ab01:{}】", ab01);
        return ab01;
    }

    /**
     * url?aab002=xxx
     * 也不需要@RequestParam注解
     */
    @GetMapping("/findByAab002")
    public List<Ab01> findByQueryObj(@RequestParam final String aab002) {
        log.info("【aab002:{}】", aab002);
        final Ab01 ab01 = new Ab01();
        ab01.setAab002(aab002);
        return Arrays.asList(ab01);
    }

    @RequestMapping("/exception")
    public void exception() {
        final int i = 1 / 0;
    }

    @GetMapping("/delay")
    public String delay(final long seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
        return String.format("delay %d secs.", seconds);
    }

    @GetMapping("/")
    public String index() {
        return "/";
    }
}
