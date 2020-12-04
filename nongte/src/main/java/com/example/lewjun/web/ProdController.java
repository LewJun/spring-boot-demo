package com.example.lewjun.web;

import com.example.lewjun.domain.Product;
import com.example.lewjun.domain.Region;
import com.example.lewjun.domain.result.ProductListQueryResult;
import com.example.lewjun.domain.vo.ProductQueryParamVO;
import com.example.lewjun.mapper.ProductMapper;
import com.example.lewjun.mapper.RegionMapper;
import com.example.lewjun.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        log.info("param {}", param);
        Map<String, Object> map = new HashMap<>(2);
        map.put("rows", productMapper.queryByConditions(param));
        map.put("total", productMapper.queryCountByConditions(param));
        String json = JsonUtils.object2String(map);
        log.info("json {}", json);

        // 需要返回total和rows
        return json;
    }

    @GetMapping("/create")
    public String create() {
        return "prod/edit.html";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id, final Model model) {
        model.addAttribute("prod", productMapper.queryEditById(id));
        return "prod/edit.html";
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(final Product product) {
        log.info("【product {}】", product);
        if (product.getId() == null) {
            // 新增
            productMapper.insert(product);
        } else {
            // 修改
            productMapper.update(product);
        }
        return "ok";
    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public String changeStatus(final int id, final int status) {
        productMapper.updateStatus(id, status);
        return "ok";
    }
}
