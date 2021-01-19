package com.example.lewjun.web;

import com.example.lewjun.domain.LoginForm;
import com.example.lewjun.mapper.SysUserMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final SysUserMapper sysUserMapper;

    @Autowired
    public AuthController(final SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @GetMapping("/signin")
    public String signin(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            return "signin.html";
        }
        return "redirect:/";
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
        final String username = loginForm.getUsername();

        final String pwd = sysUserMapper.findByUsername(username);
        if (BCrypt.checkpw(loginForm.getPassword() + loginForm.getUsername(), pwd)) {
            request.getSession().setAttribute("loginUser", username);
            return "redirect:/";
        }

        model.addAttribute("err", "用户名或密码错误");
        model.addAttribute("loginForm", loginForm);
        return "signin.html";
    }
}
