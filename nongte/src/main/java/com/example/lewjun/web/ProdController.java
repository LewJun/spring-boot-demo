package com.example.lewjun.web;

import com.example.lewjun.domain.Product;
import com.example.lewjun.domain.Region;
import com.example.lewjun.domain.vo.ProductQueryParamVO;
import com.example.lewjun.mapper.ProductMapper;
import com.example.lewjun.mapper.RegionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/prod")
public class ProdController {

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/province/{code}/{name}")
    public String province(@PathVariable final Integer code, @PathVariable final String name, final Model model) {
        log.info("【province code {}】", code);

        model.addAttribute("regionTitle", name);

        model.addAttribute("path", "city");

        final List<Region> cityRegions = regionMapper.queryCitiesByProvinceCode(510000);
        log.info("【cityRegions: {}】", cityRegions);

        model.addAttribute("regions", regionMapper.queryCitiesByProvinceCode(code));

        model.addAttribute("prods", productMapper.queryByProvinceCode(code));
        return "prod/prod.html";
    }

    @GetMapping("/city/{code}/{name}")
    public String city(@PathVariable final Integer code, @PathVariable final String name, final Model model) {
        log.info("【city code {}】", code);

        model.addAttribute("regionTitle", name);

        model.addAttribute("path", "area");

        model.addAttribute("regions", regionMapper.queryAreasByCityCode(code));

        model.addAttribute("prods", productMapper.queryByCityCode(code));
        return "prod/prod.html";
    }

    @GetMapping("/area/{code}/{name}")
    public String area(@PathVariable final Integer code, @PathVariable final String name, final Model model) {
        log.info("【area code {}】", code);

        model.addAttribute("regionTitle", name);

        model.addAttribute("prods", productMapper.queryByAreaCode(code));
        return "prod/prod.html";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable final Integer id, final Model model) {
        model.addAttribute("detail", productMapper.queryDetailById(id));
        return "prod/detail.html";
    }

    @GetMapping("/list")
    public String list() {
        return "prod/list.html";
    }

    @GetMapping("/list/query")
    @ResponseBody
    public String listQuery(final ProductQueryParamVO param) {
        // 需要返回total和rows
        return "{\n" +
                "  \"rows\": [\n" +
                "    {\n" +
                "      \"desc\": \"产品描述\",\n" +
                "      \"id\": 123,\n" +
                "      \"status\": 1,\n" +
                "      \"title\": \"产品名称xxx\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"desc\": \"产品描述23\",\n" +
                "      \"id\": 1233,\n" +
                "      \"status\": 2,\n" +
                "      \"title\": \"产品名称xxserewxx\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"total\": 34\n" +
                "}";
    }

    @GetMapping("/create")
    public String create() {
        return "prod/edit.html";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id, final Model model) {
        final Product product = new Product();
        product.setId(123);
        product.setTitle("标题在这里");
        product.setDesc("描述信息");
        product.setProvince("210000");
        product.setCity("211000");
        product.setArea("211081");
        product.setLevel(2);
        product.setHtml("<p>abc</p>");
        product.setPicUrl("2020/11/f1db2952-4d97-42b2-8ad7-1c0a69595e42.jpg");
        model.addAttribute("prod", product);
        return "prod/edit.html";
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(final Product product) {
        log.info("【product {}】", product);
        return "ok";
    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public String changeStatus(final int id, final int status) {
        productMapper.updateStatus(id, status);
        return "ok";
    }
}
