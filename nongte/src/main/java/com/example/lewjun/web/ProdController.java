package com.example.lewjun.web;

import com.example.lewjun.domain.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/prod")
public class ProdController {

    @GetMapping("/p/{code}")
    public String province(@PathVariable final String code, final Model model) {
        log.info("【province code {}】", code);

        model.addAttribute("regions", Arrays.asList(
                new Region(510100, "成都市"),
                new Region(510300, "自贡市"),
                new Region(510400, "攀枝花市"),
                new Region(510500, "泸州市"),
                new Region(510600, "德阳市"),
                new Region(510700, "绵阳市"),
                new Region(510800, "广元市"),
                new Region(510900, "遂宁市"),
                new Region(511000, "内江市"),
                new Region(511100, "乐山市"),
                new Region(511300, "南充市"),
                new Region(511400, "眉山市"),
                new Region(511500, "宜宾市"),
                new Region(511600, "广安市"),
                new Region(511700, "达州市"),
                new Region(511800, "雅安市"),
                new Region(511900, "巴中市"),
                new Region(512000, "资阳市"),
                new Region(513200, "阿坝藏族羌族自治州"),
                new Region(513300, "甘孜藏族自治州"),
                new Region(513400, "凉山彝族自治州")
        ));

        model.addAttribute("msg", "hello world");


        return "prod/province.html";
    }


    @GetMapping("/c/{code}")
    public String city(@PathVariable final String code) {
        log.info("【city code {}】", code);
        return "prod/city.html";
    }


    @GetMapping("/a/{code}")
    public String area(@PathVariable final String code) {
        log.info("【area code {}】", code);
        return "prod/area.html";
    }


}
