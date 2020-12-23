package com.example.lewjun.web;

import com.example.lewjun.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private RegionMapper regionMapper;

    @Autowired
    public IndexController(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("provs", regionMapper.queryAllProvs());
        return "index.html";
    }
}
