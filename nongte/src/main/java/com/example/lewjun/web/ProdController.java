package com.example.lewjun.web;

import com.example.lewjun.domain.Product;
import com.example.lewjun.domain.Region;
import com.example.lewjun.domain.vo.ProductQueryParamVO;
import com.example.lewjun.mapper.ProductMapper;
import com.example.lewjun.mapper.RegionMapper;
import com.example.lewjun.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/prod")
public class ProdController {

    private final String OK = "ok";
    private final String ERR = "err";
    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/province/{code}/{name}")
    public String province(@PathVariable final Integer code, @PathVariable final String name, final Model model) {
        model.addAttribute("regionTitle", name);

        if (code == 110000 // 北京市
                || code == 120000 // 天津市
                || code == 500000 // 重庆市
                || code == 310000 // 上海市
        ) {
            // 按照需求方要求，对直辖市特殊处理
            handleMunicipality(code, model);
            model.addAttribute("prods", productMapper.queryByRegionCode(code, null, null));
        } else if (code == 710000 || code == 720000 || code == 730000) {
            model.addAttribute("path", "area");
        } else {
            model.addAttribute("path", "city");
            model.addAttribute("regions", regionMapper.queryCitiesByProvinceCode(code));
            model.addAttribute("prods", productMapper.queryByRegionCode(code, null, null));
        }
        return "prod/prod.html";
    }

    private void handleMunicipality(final Integer code, final Model model) {
        model.addAttribute("path", "area");

        final List<Region> regions = new ArrayList<>(10);
        for (final Region region : regionMapper.queryCitiesByProvinceCode(code)) {
            regions.addAll(regionMapper.queryAreasByCityCode(region.getCode()));
        }
        model.addAttribute("regions", regions);
    }

    @GetMapping("/city/{code}/{name}")
    public String city(@PathVariable final Integer code, @PathVariable final String name, final Model model) {
        model.addAttribute("regionTitle", name);

        model.addAttribute("path", "area");

        model.addAttribute("regions", regionMapper.queryAreasByCityCode(code));

        model.addAttribute("prods", productMapper.queryByRegionCode(null, code, null));
        return "prod/prod.html";
    }

    @GetMapping("/area/{code}")
    @ResponseBody
    public String queryProdByAreaCode(@PathVariable final Integer code) {
        return JsonUtils.object2String(productMapper.queryByRegionCode(null, null, code));
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
        final Map<String, Object> map = new HashMap<>(2);
        map.put("rows", productMapper.queryByConditions(param));
        map.put("total", productMapper.queryCountByConditions(param));
        return JsonUtils.object2String(map);
    }

    @GetMapping("/search")
    public String search(final Model model, @RequestParam("s") final String s,
                         @RequestParam(name = "pageNumber", required = false, defaultValue = "20") final Integer pageNumber,
                         @RequestParam(name = "offset", required = false, defaultValue = "0") final Integer offset) {
        model.addAttribute("prods", productMapper.queryByKeywords(s, pageNumber, offset));
        model.addAttribute("s", s);
        return "prod/query.html";
    }

    @GetMapping("/create")
    public String create(final Model model) {
        model.addAttribute("prod", new Product());
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
        if (product.getCity_code() == null) {
            product.setCity_code(0);
        }

        if (StringUtils.isEmpty(product.getCity_name())) {
            product.setCity_name("");
        }

        if (product.getArea_code() == null) {
            product.setArea_code(0);
        }

        if (StringUtils.isEmpty(product.getArea_code())) {
            product.setArea_name("");
        }

        if (product.getDb() == null) {
            product.setDb(0);
        }

        if (product.getId() == null) {
            productMapper.insert(product);
        } else {
            productMapper.update(product);
        }
        return OK;
    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public String changeStatus(final int id, final int status) {
        productMapper.updateStatus(id, status);
        return OK;
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public String changeStatus(@PathVariable final int id) {
        productMapper.delete(id);
        return OK;
    }
}
