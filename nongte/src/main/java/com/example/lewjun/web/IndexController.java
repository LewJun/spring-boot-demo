package com.example.lewjun.web;

import com.example.lewjun.domain.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Slf4j
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(final Model model) {
        log.info("【index】");
        model.addAttribute("msg", "hello world");

        model.addAttribute("regions", Arrays.asList(
                new Region(110000, "北京市"),
                new Region(120000, "天津市"),
                new Region(130000, "河北省"),
                new Region(140000, "山西省"),
                new Region(150000, "内蒙古自治区"),
                new Region(210000, "辽宁省"),
                new Region(220000, "吉林省"),
                new Region(230000, "黑龙江省"),
                new Region(310000, "上海市"),
                new Region(320000, "江苏省"),
                new Region(330000, "浙江省"),
                new Region(340000, "安徽省"),
                new Region(350000, "福建省"),
                new Region(360000, "江西省"),
                new Region(370000, "山东省"),
                new Region(410000, "河南省"),
                new Region(420000, "湖北省"),
                new Region(430000, "湖南省"),
                new Region(440000, "广东省"),
                new Region(450000, "广西壮族自治区"),
                new Region(460000, "海南省"),
                new Region(500000, "重庆市"),
                new Region(510000, "四川省"),
                new Region(520000, "贵州省"),
                new Region(530000, "云南省"),
                new Region(540000, "西藏自治区"),
                new Region(610000, "陕西省"),
                new Region(620000, "甘肃省"),
                new Region(630000, "青海省"),
                new Region(640000, "宁夏回族自治区"),
                new Region(650000, "新疆维吾尔自治区")
        ));
        return "index.html";
    }
}
