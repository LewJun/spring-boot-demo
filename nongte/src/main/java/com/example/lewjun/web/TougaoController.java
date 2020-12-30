package com.example.lewjun.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TougaoController {
    @GetMapping("/tougao")
    public String tougao() {
        return "tougao.html";
    }
}
