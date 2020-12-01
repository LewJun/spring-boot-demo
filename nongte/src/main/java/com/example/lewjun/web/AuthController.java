package com.example.lewjun.web;

import com.example.lewjun.domain.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @GetMapping("/signin")
    public String signin(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            return "signin.html";
        }
        return "redirect:prod/list";
    }

    @GetMapping("/signout")
    public String signout(final HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(final HttpServletRequest request, final Model model, final LoginForm loginForm) {
        final HttpSession session = request.getSession();
        final String username = loginForm.getUsername();
        if ("admin".equals(username) && "admin123password".equals(loginForm.getPassword())) {
            session.setAttribute("loginUser", username);
            return "redirect:prod/list";
        } else {
            model.addAttribute("err", "用户名或密码错误");
            model.addAttribute("loginForm", loginForm);
            return "signin.html";
        }
    }
}
