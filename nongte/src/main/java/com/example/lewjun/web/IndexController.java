package com.example.lewjun.web;

import com.example.lewjun.domain.Region;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("regions", Arrays.asList(
                new Region(110000, "北京"),
                new Region(120000, "天津"),
                new Region(130000, "河北"),
                new Region(140000, "山西"),
                new Region(150000, "内蒙古"),
                new Region(210000, "辽宁"),
                new Region(220000, "吉林"),
                new Region(230000, "黑龙江"),
                new Region(310000, "上海"),
                new Region(320000, "江苏"),
                new Region(330000, "浙江"),
                new Region(340000, "安徽"),
                new Region(350000, "福建"),
                new Region(360000, "江西"),
                new Region(370000, "山东"),
                new Region(410000, "河南"),
                new Region(420000, "湖北"),
                new Region(430000, "湖南"),
                new Region(440000, "广东"),
                new Region(450000, "广西"),
                new Region(460000, "海南"),
                new Region(500000, "重庆"),
                new Region(510000, "四川"),
                new Region(520000, "贵州"),
                new Region(530000, "云南"),
                new Region(540000, "西藏"),
                new Region(610000, "陕西"),
                new Region(620000, "甘肃"),
                new Region(630000, "青海"),
                new Region(640000, "宁夏"),
                new Region(650000, "新疆"),
                new Region(710000, "香港"),
                new Region(720000, "澳门"),
                new Region(730000, "台湾")
        ));
        return "index.html";
    }
}
