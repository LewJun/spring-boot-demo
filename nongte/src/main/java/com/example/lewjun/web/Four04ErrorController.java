package com.example.lewjun.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Four04ErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String error() {
        // 如果访问了不存在的页面，直接返回到首页。
        return "redirect:/";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
