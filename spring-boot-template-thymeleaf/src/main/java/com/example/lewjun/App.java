package com.example.lewjun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

/**
 * spring boot 启动类
 */
@SpringBootApplication
@Controller
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("msg", "hello world");
        model.addAttribute("now", new Date());
        return "index";
    }
}
