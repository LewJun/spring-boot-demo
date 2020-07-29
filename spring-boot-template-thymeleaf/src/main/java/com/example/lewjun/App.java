package com.example.lewjun;

import com.example.lewjun.domain.Ab01;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
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

        model.addAttribute("th_id", "th:id 替换id");
        model.addAttribute("th_text", "th:text <h2>这里返回的是h2标签</h2>");
        model.addAttribute("th_utext", "th:utext 替换html <h2>这里返回的是h2标签</h2>"); // <p th:utext="html content">default content</p>
        model.addAttribute("th_value", "th:value 替换表单值"); // <input th:value="${xxx}" />
        model.addAttribute("th_each", "thEach 迭代"); // <tr th:each="ab01:${ab01s}"></tr>
        model.addAttribute("th_src", "images/img.jpg"); //
        model.addAttribute("th_href", "http://2b.com"); //
//        model.addAttribute("th:src", "thSrc 替换资源"); //

        model.addAttribute("ab01", new Ab01(10, "aab002", "aab003"));

        model.addAttribute("ab01sHeaders", Arrays.asList("AAB001", "AAB002", "AAB003"));
        model.addAttribute("ab01s", Arrays.asList(
                new Ab01(10, "aab002", "aab003"),
                new Ab01(20, "0002", "0003"),
                new Ab01(30, "2222", "3333"),
                new Ab01(40, "4444", "5555")
        ));

        return "index";
    }
}
