package com.example.lewjun.web;

import com.example.lewjun.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    public HttpSession session;

    @PostMapping("/user/login")
    public String login(Model model, UserInfo userInfo) {
        session.setAttribute("loginUser", userInfo);

        model.addAttribute("loginUser", userInfo);
        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String login() {
        return "user/login";
    }
}
