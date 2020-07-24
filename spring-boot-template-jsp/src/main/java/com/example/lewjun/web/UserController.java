package com.example.lewjun.web;

import com.example.lewjun.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @PostMapping("/user/login")
    public ModelAndView login(UserInfo userInfo, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        mv.addObject(userInfo);
        mv.setViewName("redirect:/");

        request.getSession().setAttribute("user", userInfo);
        return mv;
    }

    @GetMapping("/user/login")
    public ModelAndView login() {
        return new ModelAndView("user/login");
    }
}
