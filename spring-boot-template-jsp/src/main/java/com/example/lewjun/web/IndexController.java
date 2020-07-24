package com.example.lewjun.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @GetMapping({"", "/", "/index"})
    public ModelAndView index(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("index");

        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            mv.setViewName("redirect:/user/login");
            return mv;
        }

        mv.addObject("user", user);
        return mv;
    }
}
