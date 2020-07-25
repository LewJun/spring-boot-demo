package com.example.lewjun.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    public HttpSession session;

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        Object user = session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/user/login";
        }

        model.addAttribute("loginUser", user);

        return "index";
    }
}