package com.example.lewjun.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeclareController {
    @GetMapping("/declare")
    public String declare() {
        return "declare.html";
    }
}
