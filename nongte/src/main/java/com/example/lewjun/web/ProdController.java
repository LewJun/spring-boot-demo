package com.example.lewjun.web;

import com.example.lewjun.domain.Product;
import com.example.lewjun.domain.Region;
import com.example.lewjun.domain.vo.ProductQueryParamVO;
import com.example.lewjun.enums.EnumRegionType;
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

    private final int limit = 20;
    private final String OK = "ok";
    private final String ERR = "err";

    private final RegionMapper regionMapper;
    private final ProductMapper productMapper;

    @Autowired
    public ProdController(final RegionMapper regionMapper, final ProductMapper productMapper) {
        this.regionMapper = regionMapper;
        this.productMapper = productMapper;
    }

    @GetMapping("/province/{code}/{name}")
    public String province(@PathVariable final Integer code, @PathVariable final String name
            , @RequestParam(name = "page", required = false, defaultValue = "1") final Integer page, final Model model) {
        model.addAttribute("regionTitle", name);
        model.addAttribute("code", code);

        String regionType = EnumRegionType.province.name();

        handleCurrentPageAndLimit(model, page);

        if (code == 110000 // 北京市
                || code == 120000 // 天津市
                || code == 500000 // 重庆市
                || code == 310000 // 上海市
        ) {
            // 按照需求方要求，对直辖市特殊处理
            handleMunicipality(code, model);

            regionType = EnumRegionType.city.name();
        } else if (code == 710000 || code == 720000 || code == 730000) {
            model.addAttribute("path", EnumRegionType.area.name());
        } else {
            model.addAttribute("path", EnumRegionType.city.name());
            model.addAttribute("regions", regionMapper.queryCitiesByProvinceCode(code));
        }
        model.addAttribute("prods", productMapper.queryByRegionCode(code, null, null,
                limit, getOffset(page))
        );

        final int total = productMapper.queryCountByRegionCode(code, null, null);
        handleTotalPages(model, total);

        model.addAttribute("regionType", regionType);

        return "prod/prod.html";
    }

    private void handleCurrentPageAndLimit(final Model model, @RequestParam(name = "page", required = false, defaultValue = "1") final Integer page) {
        model.addAttribute("currentPage", page);
        model.addAttribute("limit", limit);
    }

    private void handleTotalPages(final Model model, final int total) {
        final int totalPages = total > limit ? (total % limit == 0 ? total / limit : (total / limit) + 1) : 1;
        model.addAttribute("totalPages", totalPages);
    }

    private void handleMunicipality(final Integer code, final Model model) {
        model.addAttribute("path", EnumRegionType.area.name());

        final List<Region> regions = new ArrayList<>(10);
        for (final Region region : regionMapper.queryCitiesByProvinceCode(code)) {
            regions.addAll(regionMapper.queryAreasByCityCode(region.getCode()));
        }
        model.addAttribute("regions", regions);
    }

    @GetMapping("/city/{code}/{name}")
    public String city(@PathVariable final Integer code, @PathVariable final String name, final Model model
            , @RequestParam(name = "page", required = false, defaultValue = "1") final Integer page) {
        model.addAttribute("regionTitle", name);
        model.addAttribute("code", code);

        handleCurrentPageAndLimit(model, page);

        model.addAttribute("path", EnumRegionType.area.name());

        model.addAttribute("regions", regionMapper.queryAreasByCityCode(code));

        model.addAttribute("prods", productMapper.queryByRegionCode(null, code,
                null, limit, getOffset(page)));
        final int total = productMapper.queryCountByRegionCode(null, code, null);
        handleTotalPages(model, total);

        model.addAttribute("regionType", EnumRegionType.city.name());

        return "prod/prod.html";
    }

    private int getOffset(final Integer page) {
        return (page - 1) * limit;
    }

    @GetMapping("/area/{code}")
    public String area(@PathVariable final Integer code,
                       @RequestParam("cityCode") final Integer cityCode,
                       @RequestParam("cityName") final String cityName,
                       @RequestParam(name = "page", required = false, defaultValue = "1") final Integer page,
                       final Model model) {
        model.addAttribute("regionTitle", cityName);
        model.addAttribute("code", cityCode);
        model.addAttribute("areaCode", code);

        handleCurrentPageAndLimit(model, page);

        model.addAttribute("path", EnumRegionType.area.name());


        if (cityCode == 110000 // 北京市
                || cityCode == 120000 // 天津市
                || cityCode == 500000 // 重庆市
                || cityCode == 310000 // 上海市
        ) {
            final List<Region> regions = new ArrayList<>();
            for (final Region city : regionMapper.queryCitiesByProvinceCode(cityCode)) {
                regions.addAll(regionMapper.queryAreasByCityCode(city.getCode()));
            }
            model.addAttribute("regions", regions);
        } else if (cityCode == 710000 || cityCode == 720000 || code == 730000) {

        } else {
            model.addAttribute("regions", regionMapper.queryAreasByCityCode(cityCode));
        }

        model.addAttribute("prods", productMapper.queryByRegionCode(null, null,
                code, limit, getOffset(page)));
        final int total = productMapper.queryCountByRegionCode(null, null, code);
        handleTotalPages(model, total);

        model.addAttribute("regionType", EnumRegionType.city.name());
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
        final Map<String, Object> map = new HashMap<>(2);
        map.put("rows", productMapper.queryByConditions(param));
        map.put("total", productMapper.queryCountByConditions(param));
        return JsonUtils.object2String(map);
    }

    @GetMapping("/search")
    public String search(final Model model, @RequestParam("s") final String s,
                         @RequestParam(name = "page", required = false, defaultValue = "1") final Integer page) {
        model.addAttribute("prods", productMapper.queryByKeywords(s, limit, getOffset(page)));
        model.addAttribute("s", s);
        final int total = productMapper.queryCountByKeywords(s);
        handleCurrentPageAndLimit(model, page);
        handleTotalPages(model, total);
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
